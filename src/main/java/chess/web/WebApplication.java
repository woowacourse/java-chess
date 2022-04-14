package chess.web;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.dao.DatabaseBoardDao;
import chess.dao.DatabaseGameDao;
import chess.service.ChessService;
import chess.web.controller.WebChessController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        port(8080);
        WebChessController controller = new WebChessController(
                new ChessService(new DatabaseBoardDao(), new DatabaseGameDao()));
        controller.run();
    }
}
