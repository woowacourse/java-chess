package chess;

import chess.controller.CommandController;
import chess.controller.Controller;
import chess.dao.*;
import chess.domain.ChessGame;

import java.sql.SQLException;

public final class ChessGameApplication {
    public static void main(String[] args) throws SQLException {
        CommandController commandController = CommandController.create();

        GameDao gameDao = new GameJdbcDao(ConnectionGenerator.getConnection());
        BoardDao boardDao = new BoardJdbcDao(ConnectionGenerator.getConnection());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ConnectionGenerator.getConnection());

        ChessGame chessGame = ChessGame.from(gameDao, boardDao, jdbcTemplate);

        Controller controller = new Controller(commandController, chessGame);
        controller.run();
    }
}
