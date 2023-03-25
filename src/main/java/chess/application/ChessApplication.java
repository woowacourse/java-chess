package chess.application;

import chess.controller.ChessController;
import chess.service.ChessGameService;
import chess.service.PieceService;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new ChessGameService(), new PieceService());
        chessController.run();
    }
}
