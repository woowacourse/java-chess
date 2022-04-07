package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.view.ChessMap;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;

    public void run() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = initializeChessGame(whitePlayer, blackPlayer);
        progressGame(chessGame, whitePlayer, blackPlayer);
        showWinner(chessGame, whitePlayer, blackPlayer);
        showScore(chessGame, whitePlayer, blackPlayer);
    }

    private ChessGame initializeChessGame(final Player whitePlayer, final Player blackPlayer) {
        InputView.requestStartCommand();
        return new ChessGame(whitePlayer, blackPlayer);
    }

    private void showMap(final ChessMap chessMap) {
        OutputView.printChessMap(chessMap.getChessMap());
    }

    private void progressGame(final ChessGame chessGame, final Player currentPlayer, final Player opponentPlayer) {
        showMap(chessGame.createMap());
        if (isTurnFinished(chessGame, currentPlayer, opponentPlayer)) {
            return;
        }
        progressGame(chessGame, opponentPlayer, currentPlayer);
    }

    private boolean isTurnFinished(final ChessGame chessGame, final Player currentPlayer, final Player opponentPlayer) {
        try {
            final String command = InputView.requestGameCommand();
            final boolean isFinished = progressTurn(chessGame, command, currentPlayer, opponentPlayer);
            return isFinished || !chessGame.isRunning();
        } catch (final IllegalArgumentException e) {
            OutputView.printException(e);
            return isTurnFinished(chessGame, currentPlayer, opponentPlayer);
        }
    }

    private boolean progressTurn(final ChessGame chessGame, final String command,
                                 final Player currentPlayer, final Player opponentPlayer) {
        if (command.contains("move")) {
            moveTurn(chessGame, command, currentPlayer, opponentPlayer);
            return false;
        }
        if (command.equals("status")) {
            showScore(chessGame, currentPlayer, opponentPlayer);
            return isTurnFinished(chessGame, currentPlayer, opponentPlayer);
        }
        return true;
    }

    private void moveTurn(final ChessGame chessGame, final String command,
                          final Player currentPlayer, final Player opponentPlayer) {
        final String[] moveCommand = command.split(MOVE_COMMAND_DELIMITER);
        final Position currentPosition = Position.of(moveCommand[CURRENT_POSITION_INDEX]);
        final Position destinationPosition = Position.of(moveCommand[DESTINATION_POSITION_INDEX]);
        chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition);
    }

    private void showWinner(final ChessGame chessGame, final Player whitePlayer, final Player blackPlayer) {
        final String currentPlayerName = whitePlayer.getTeamName();
        final String opponentPlayerName = blackPlayer.getTeamName();

        final boolean isWhiteWin = chessGame.isWin(whitePlayer, blackPlayer);
        final boolean isBlackWin = chessGame.isWin(blackPlayer, whitePlayer);
        OutputView.printWinner(currentPlayerName, opponentPlayerName, isWhiteWin, isBlackWin);
    }

    private void showScore(final ChessGame chessGame, final Player currentPlayer, final Player opponentPlayer) {
        final String currentPlayerName = currentPlayer.getTeamName();
        final String opponentPlayerName = opponentPlayer.getTeamName();

        if (isFinishedByCaptureKing(chessGame, currentPlayer, opponentPlayer)) {
            return;
        }
        final double currentPlayerScore = chessGame.getPlayerScore(currentPlayer);
        final double opponentPlayerScore = chessGame.getPlayerScore(opponentPlayer);
        OutputView.printResult(currentPlayerName, currentPlayerScore, opponentPlayerName, opponentPlayerScore);
    }

    private boolean isFinishedByCaptureKing(final ChessGame chessGame,
                                            final Player currentPlayer, final Player opponentPlayer) {
        final boolean isOpponentPlayerWin = chessGame.hasNoKing(currentPlayer);
        final boolean isCurrentPlayerWin = chessGame.hasNoKing(opponentPlayer);
        return isCurrentPlayerWin || isOpponentPlayerWin;
    }
}
