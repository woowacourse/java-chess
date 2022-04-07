package chess.web;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.service.ChessService;
import chess.web.controller.ChessController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        port(8080);
        ChessController controller = new ChessController(new ChessService(new BoardDao(), new GameDao()));
        controller.run();
    }
}
