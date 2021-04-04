package chess;

import chess.console.ChessController;

public class WebUIChessApplication {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }

}
