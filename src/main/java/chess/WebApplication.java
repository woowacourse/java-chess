package chess;

import chess.controller.web.GameController;
import chess.controller.web.RoomController;
import chess.service.dao.DBConfig;

import java.sql.Connection;

import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        final DBConfig dbConfig = new DBConfig();
        final Connection connection = dbConfig.getConnection();

        final GameController gameController = new GameController(connection);
        final RoomController roomController = new RoomController(connection);

        gameController.mapping();
        roomController.mapping();
    }
}
