package chess;

import chess.conroller.ChessController;

public class Application {

    public static void main(String[] args) {
        final ChessController chessController = new ChessController();
        chessController.runChess();
    }
}
