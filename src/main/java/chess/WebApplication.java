package chess;

import chess.controller.ChessWebController;
import chess.dao.ChessBoardDAO;
import chess.dao.ChessGameDAO;
import chess.service.ChessService;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebApplication {

    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        new ChessWebController(new ChessService(new ChessBoardDAO(DBConnector.getConnection()),
                new ChessGameDAO(DBConnector.getConnection()))).run();
    }
}
