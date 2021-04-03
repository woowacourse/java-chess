package chess;

import chess.controller.ChessWebController;

public class WebUIChessApplication {

    public static void main(String[] args) {
        ChessWebController chessWebController = new ChessWebController();
        chessWebController.run();
    }
}
