package chess;

import chess.controller.WebUIChessController;

public final class WebUIChessApplication {

    public static void main(String[] args) {
        final WebUIChessController controller = new WebUIChessController();
        controller.runChess();
    }

}
