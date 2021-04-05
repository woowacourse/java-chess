package chess;

import chess.controller.web.GameController;
import chess.controller.web.RoomController;
import chess.service.dao.DBConfig;

import java.sql.Connection;

import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        DBConfig dbConfig = new DBConfig();
        Connection connection = dbConfig.getConnection();

        GameController gameController = new GameController(connection);
        RoomController roomController = new RoomController(connection);

        gameController.mapping();
        roomController.mapping();
    }
}
