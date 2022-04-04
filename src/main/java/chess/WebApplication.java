package chess;

import static spark.Spark.*;

import chess.controller.ChessController;
import chess.controller.JsonTransformer;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");

        final ChessController controller = new ChessController();
        get("/", controller::home);
        post("/start", controller::start);
        get("/game/:boardId", controller::game, new JsonTransformer());
        post("/move/:boardId", "application/json", controller::move);
        get("/score/:boardId", controller::score, new JsonTransformer());

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }
}
