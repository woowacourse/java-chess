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
        while (chessGame.isNotEnd()) {
            List<String> userInput = InputView.inputMoveOrStatus();
            makeScore(chessGame, userInput);
            move(chessGame, userInput);
        }
    }

    private void makeScore(ChessGame chessGame, List<String> userInput) {
        if(userInput.get(0).equals(STATUS)) {
            OutputView.printScore(chessGame.calculateScore());
        }
    }

    private void move(ChessGame chessGame, List<String> userInput) {
        if(userInput.get(0).equals(MOVE)) {
            Point source = Point.of(userInput.get(1));
            Point target = Point.of(userInput.get(2));
            chessGame.playTurn(source, target);
            OutputView.printBoard(chessGame);
        }
    }
}
