package chess;

import chess.controller.ChessGameController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new InputView(), new OutputView());
        chessGameController.run();
    }
}
