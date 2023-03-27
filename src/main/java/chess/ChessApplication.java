package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.dao.JdbcTemplate;
import chess.dao.PieceDao;
import chess.service.ChessService;

public class ChessApplication {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        ChessController chessController = new ChessController(
            new ChessService(new ChessGameDao(jdbcTemplate), new PieceDao(jdbcTemplate)));
        chessController.run();
    }

}
