package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Point;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public static final String STATUS = "status";
    public static final String MOVE = "move";

    public void run() {
        while (playerWantStart()) {
            ChessGame chessGame = new ChessGame();
            playGame(chessGame);
            OutputView.noticeGameFinished();
        }
    }

    private boolean playerWantStart() {
        try {
            OutputView.printStartGuideMessage();
            return InputView.inputStart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return playerWantStart();
        }
    }

    private void playGame(ChessGame chessGame) {
        try {
            OutputView.printBoard(chessGame);
            playTurn(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playGame(chessGame);
        }
    }

    private void playTurn(ChessGame chessGame) {
        while (chessGame.isProgressing()) {
            List<String> userInput = InputView.inputEachTurn();
            makeScoreOrMove(chessGame, userInput);
        }
    }

    private void makeScoreOrMove(ChessGame chessGame, List<String> userInput) {
        String operation = userInput.get(0);

        if (STATUS.equals(operation)) {
            OutputView.printScore(chessGame.calculateScore());
        }
        if (MOVE.equals(operation)) {
            Point source = Point.of(userInput.get(1));
            Point target = Point.of(userInput.get(2));
            chessGame.playTurn(source, target);
            OutputView.printBoard(chessGame);
        }
    }
}
