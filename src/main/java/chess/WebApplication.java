package chess;

import static spark.Spark.*;

import chess.controller.ChessController;
import chess.controller.JsonTransformer;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8083);
        staticFileLocation("/static");

        final ChessController controller = new ChessController();
        get("/", ((request, response) -> controller.home()), new HandlebarsTemplateEngine());
        post("/start", controller::start, new HandlebarsTemplateEngine());
        get("/game/:boardId", controller::game, new HandlebarsTemplateEngine());
        post("/move/:boardId", "application/json", controller::move);
        get("/score/:boardId", controller::score, new JsonTransformer());

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }
}
