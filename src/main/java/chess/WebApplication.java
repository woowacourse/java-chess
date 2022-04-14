package chess;

import chess.controller.ChessWebController;

public class WebApplication {

    public static void main(String[] args) {
        final ChessWebController chessWebController = new ChessWebController();

        chessWebController.run();
    }
}
