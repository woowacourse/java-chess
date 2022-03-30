package web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import web.controller.ChessGameController;
import web.service.ChessGameService;
import web.dao.ChessBoardDao;
import web.dao.GameStatusDao;
import web.dao.ScoreDao;

public class WebApplication {

    public static void main(String[] args) {
        ChessGameService service = new ChessGameService(new ChessBoardDao(), new ScoreDao(), new GameStatusDao());
        ChessGameController controller = new ChessGameController(service);

        port(8080);
        staticFileLocation("/static");

        get("/", controller::index);
        post("/move", controller::move);
    }
}
