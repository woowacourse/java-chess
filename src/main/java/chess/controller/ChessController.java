package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Team;
import chess.domain.winningstatus.Score;
import chess.domain.winningstatus.WinningStatus;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;
import java.util.function.Consumer;

public final class ChessController {

    public void run() {
        OutputView.printWelcomeMessage();
        final ChessGame chessGame = new ChessGame();

        do {
            checkException(this::playGame, chessGame);
        } while (chessGame.isNotEnd());
    }

    private <T> void checkException(final Consumer<T> consumer, final T parameter) {
        try {
            consumer.accept(parameter);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void playGame(final ChessGame chessGame) {
        final Command command = InputView.readCommand();
        processCommand(chessGame, command);
    }

    private void processCommand(final ChessGame chessGame, final Command command) {
        if (command == Command.START) {
            startGame(chessGame);
        }
        if (command == Command.MOVE) {
            movePiece(chessGame);
        }
        if (command == Command.STATUS) {
            showStatus(chessGame.status());
        }
        if (command == Command.END) {
            chessGame.end();
        }
    }

    private void startGame(final ChessGame chessGame) {
        chessGame.start();
        showChessBoard(chessGame.getChessBoard());
    }

    private void showChessBoard(final ChessBoard chessBoard) {
        ChessBoardFormatter convertedChessBoard = ChessBoardFormatter.toString(chessBoard);
        OutputView.printChessBoard(convertedChessBoard);
    }

    private void showStatus(final WinningStatus winningStatus) {
        if (winningStatus.isWinnerDetermined()) {
            showStatusAfterRunning(winningStatus);
            return;
        }
        showStatusWhenRunning(winningStatus);
    }

    private void showStatusAfterRunning(final WinningStatus winningStatus) {

    }

    private void showStatusWhenRunning(final WinningStatus winningStatus) {
        final Map<Team, Score> scores = winningStatus.getScores();
        for (Team team : scores.keySet()) {
            OutputView.printScore(TeamName.getNameByTeam(team), scores.get(team).getScore());
        }

        Team winner = winningStatus.getWinner();
        if (winner == null) {
            OutputView.printDrawWhenRunning();
            return;
        }
        OutputView.printWinnerWhenRunning(TeamName.getNameByTeam(winner));
    }

    private void movePiece(final ChessGame chessGame) {
        final SquareCoordinate from = SquareCoordinate.of(InputView.getCoordinate());
        final SquareCoordinate to = SquareCoordinate.of(InputView.getCoordinate());

        chessGame.move(from, to);
        showChessBoard(chessGame.getChessBoard());
    }
}
