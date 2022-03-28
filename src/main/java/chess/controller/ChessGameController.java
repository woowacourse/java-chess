package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.utils.ChessMap;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {

    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;

    public void run() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = initializeChessGame(whitePlayer, blackPlayer);
        showMap(chessGame.createMap());
        progressGame(chessGame, whitePlayer, blackPlayer);
        showWinner(chessGame, whitePlayer, blackPlayer);
        if (!isFinishedByCaptureKing(chessGame, whitePlayer, blackPlayer)) {
            showScore(chessGame, whitePlayer, blackPlayer);
        }
    }

    private ChessGame initializeChessGame(final Player whitePlayer, final Player blackPlayer) {
        InputView.requestStartCommand();
        return new ChessGame(whitePlayer, blackPlayer);
    }

    private void showMap(final ChessMap chessMap) {
        OutputView.printChessMap(chessMap.getChessMap());
    }

    private void progressGame(final ChessGame chessGame, final Player whitePlayer, final Player blackPlayer) {
        if (isTurnFinished(chessGame, whitePlayer, blackPlayer)) {
            return;
        }
        showMap(chessGame.createMap());
        if (isTurnFinished(chessGame, blackPlayer, whitePlayer)) {
            return;
        }
        showMap(chessGame.createMap());
        progressGame(chessGame, whitePlayer, blackPlayer);
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
        List<Position> positions =
                findMoveCommandPosition(moveCommand[CURRENT_POSITION_INDEX], moveCommand[DESTINATION_POSITION_INDEX]);
        chessGame.move(currentPlayer, opponentPlayer,
                positions.get(POSITION_FILE_INDEX), positions.get(POSITION_RANK_INDEX));
    }

    private List<Position> findMoveCommandPosition(final String currentPosition, final String destinationPosition) {
        final char currentFile = currentPosition.charAt(POSITION_FILE_INDEX);
        final int currentRank = currentPosition.charAt(POSITION_RANK_INDEX) - '0';
        final char destinationFile = destinationPosition.charAt(POSITION_FILE_INDEX);
        final int destinationRank = destinationPosition.charAt(POSITION_RANK_INDEX) - '0';
        return List.of(new Position(currentRank, currentFile), new Position(destinationRank, destinationFile));
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
