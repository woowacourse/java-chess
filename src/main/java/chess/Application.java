package chess;

import chess.controller.ChessGameController;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new OutputView(), new InputView());
        chessGameController.run();
    }
}
