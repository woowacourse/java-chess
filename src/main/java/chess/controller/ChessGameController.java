package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessMap;
import chess.domain.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = initializeChessGame(whitePlayer, blackPlayer);
        showChessMap(chessGame.createMap());
        playGame(chessGame, whitePlayer, blackPlayer);
        showPlayerResult(whitePlayer, blackPlayer);
    }

    private ChessGame initializeChessGame(final Player whitePlayer, final Player blackPlayer) {
        InputView.requestStartCommand();
        return new ChessGame(whitePlayer, blackPlayer);
    }

    private void showChessMap(final ChessMap chessMap) {
        OutputView.printChessMap(chessMap.getChessMap());
    }

    private void playGame(final ChessGame chessGame, final Player whitePlayer, final Player blackPlayer) {
        if (!runCurrentPlayerTurn(chessGame, whitePlayer, blackPlayer)) {
            return;
        }
        if (!runCurrentPlayerTurn(chessGame, blackPlayer, whitePlayer)) {
            return;
        }
        playGame(chessGame, whitePlayer, blackPlayer);
    }

    private boolean runCurrentPlayerTurn(final ChessGame chessGame, final Player currentPlayer, final Player opponentPlayer) {
        final boolean isEndTurn = turn(chessGame, currentPlayer, opponentPlayer);
        if (!isRunningChessGame(isEndTurn, chessGame)) {
            return false;
        }
        showChessMap(chessGame.createMap());
        return true;
    }

    private boolean turn(final ChessGame chessGame, final Player currentPlayer, final Player opponentPlayer) {
        try {
            final String[] command = InputView.requestGameCommand();
            return isFinishCurrentPlayerTurn(chessGame, command, currentPlayer, opponentPlayer);
        } catch (final IllegalArgumentException e) {
            OutputView.printException(e);
            return turn(chessGame, currentPlayer, opponentPlayer);
        }
    }

    private boolean isRunningChessGame(final boolean isEndTurn, final ChessGame chessGame) {
        return !isEndTurn && chessGame.isRunning();
    }

    private boolean isFinishCurrentPlayerTurn(final ChessGame chessGame, final String[] commandInput,
            final Player currentPlayer, final Player opponentPlayer) {
        final String command = commandInput[0];
        if (command.contains("move")) {
            moveTurn(chessGame, currentPlayer, opponentPlayer, commandInput);
            return false;
        }
        if (command.equals("status")) {
            showPlayerResult(currentPlayer, opponentPlayer);
            turn(chessGame, currentPlayer, opponentPlayer);
            return false;
        }
        return true;
    }

    private void moveTurn(final ChessGame chessGame, final Player currentPlayer, final Player opponentPlayer, final String[] command) {
        final List<Position> positions = findMoveCommandPosition(command[1], command[2]);
        chessGame.move(currentPlayer, opponentPlayer, positions.get(0), positions.get(1));
    }

    public List<Position> findMoveCommandPosition(final String currentPosition, final String destinationPosition) {
        final char currentFile = currentPosition.charAt(0);
        final int currentRank = currentPosition.charAt(1) - '0';
        final char destinationFile = destinationPosition.charAt(0);
        final int destinationRank = destinationPosition.charAt(1) - '0';

        return List.of(new Position(currentRank, currentFile), new Position(destinationRank, destinationFile));
    }

    private void showPlayerResult(final Player currentPlayer, final Player opponentPlayer) {
        final String currentPlayerName = currentPlayer.getTeamName();
        final String opponentPlayerName = opponentPlayer.getTeamName();

        final double currentPlayerScore = currentPlayer.calculateScore();
        final double opponentPlayerScore = opponentPlayer.calculateScore();
        OutputView.printResult(currentPlayerName, currentPlayerScore, opponentPlayerName, opponentPlayerScore);
    }
}
