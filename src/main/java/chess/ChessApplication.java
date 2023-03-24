package chess;

import chess.controller.ChessController;
import chess.repository.ChessGameDao;
import chess.repository.GameGenerationService;
import chess.repository.JdbcTemplate;
import chess.repository.PieceDao;

public class ChessApplication {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        ChessController chessController = new ChessController(
            new GameGenerationService(new ChessGameDao(jdbcTemplate), new PieceDao(jdbcTemplate)));
        chessController.run();
    }
}
