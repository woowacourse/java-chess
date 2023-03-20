package chess;

import chess.controller.ChessHandler;

public final class ChessApplication {
    public static void main(String[] args) {
        final ChessHandler chessHandler = new ChessHandler();
        chessHandler.run();
    }
}
