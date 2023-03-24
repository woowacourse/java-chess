package chess;

import chess.controller.ChessController;
import chess.repository.ChessGameDao;
import chess.repository.GameGenerationService;
import chess.repository.PieceDao;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(
            new GameGenerationService(new ChessGameDao(), new PieceDao()));
        chessController.run();
    }
}
