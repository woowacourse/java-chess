package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Point;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void run() {
        OutputView.printStartGuideMessage();

        while (InputView.inputStart()) {
            ChessGame chessGame = new ChessGame();
            OutputView.printBoard(chessGame);

            while (chessGame.isNotEnd()) {
                List<String> points = InputView.inputMovingPiece();
                Point source = Point.of(points.get(0));
                Point target = Point.of(points.get(1));
                chessGame.playTurn(source, target);
            }
        }
    }
}
