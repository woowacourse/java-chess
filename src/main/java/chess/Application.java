package chess;

import chess.controller.ChessController;
import chess.util.ErrorUtil;

public class Application {

    public static void main(String[] args) {
        ChessController controller = new ChessController();
        ErrorUtil.computeError(controller::run);
    }
}
