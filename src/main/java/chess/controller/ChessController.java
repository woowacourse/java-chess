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
        OutputView.printStartGuideMessage();

        while (InputView.inputStart()) {
            ChessGame chessGame = new ChessGame();
            OutputView.printBoard(chessGame);
            playGame(chessGame);
            OutputView.noticeGameFinished();
        }
    }

    private void playGame(ChessGame chessGame) {
        while (chessGame.isProgressing()) {
            List<String> userInput = InputView.inputMoveOrStatus();
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
