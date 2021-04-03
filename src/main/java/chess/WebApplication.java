package chess;

import chess.controller.web.WebController;
import chess.dao.DBConfig;

import java.sql.Connection;

import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");
        DBConfig dbConfig = new DBConfig();
        Connection connection = dbConfig.getConnection();

        WebController webController = new WebController();
        webController.mapping();
    }
}
