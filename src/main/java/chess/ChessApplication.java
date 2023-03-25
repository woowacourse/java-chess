package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new ChessGame());
        chessController.run();
    }
}
