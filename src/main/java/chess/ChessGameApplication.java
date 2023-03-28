package chess;

import chess.controller.CommandController;
import chess.controller.Controller;
import chess.dao.ConnectionGenerator;
import chess.dao.GameDao;
import chess.domain.ChessGame;

import java.sql.SQLException;

public final class ChessGameApplication {
    public static void main(String[] args) throws SQLException {
        CommandController commandController = CommandController.create();
        ChessGame chessGame = ChessGame.from(new GameDao(ConnectionGenerator.getConnection()));

        Controller controller = new Controller(commandController, chessGame);
        controller.run();
    }
}
