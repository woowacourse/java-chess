package chess;

import chess.controller.ChessController;
import chess.service.PieceService;
import chess.service.GameService;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new GameService(), new PieceService());
        chessController.start();
    }
}
