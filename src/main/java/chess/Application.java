package chess;

import chess.controller.ChessGameController;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            ChessGameController chessGameController = new ChessGameController(new InputView(), new OutputView());
            chessGameController.start();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
