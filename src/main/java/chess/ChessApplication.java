package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.service.GameGenerationService;
import chess.dao.JdbcTemplate;
import chess.dao.PieceDao;

public class ChessApplication {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        ChessController chessController = new ChessController(
            new GameGenerationService(new ChessGameDao(jdbcTemplate), new PieceDao(jdbcTemplate)));
        chessController.run();
    }
}
