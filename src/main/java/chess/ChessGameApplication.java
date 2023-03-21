package chess;

import chess.controller.ChessGameController;
import chess.repository.ChessJdbcDao;
import chess.repository.ConnectionGenerator;
import chess.repository.JdbcTemplate;
import chess.service.ChessGame;

public class ChessGameApplication {
    public static void main(String[] args) {
        final ConnectionGenerator connectionGenerator = new ConnectionGenerator();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(connectionGenerator);
        final ChessJdbcDao chessDao = new ChessJdbcDao(jdbcTemplate);
        final ChessGame chessGame = new ChessGame(chessDao);
        final ChessGameController chessGameController = new ChessGameController(chessGame);
        chessGameController.run();
    }
}
