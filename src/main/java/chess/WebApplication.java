package chess;

import static spark.Spark.*;

import chess.controller.ChessController;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFileLocation("/static");
        final ChessController controller = new ChessController();
        get("/", controller.home());
        get("/game", controller.game());
        post("/move", "application/json", controller.move());
        get("/score", controller.score(), new JsonTransformer());
        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }
}
