package chess;

import static spark.Spark.*;

import chess.controller.ChessController;
import chess.controller.JsonTransformer;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(final String[] args) {
        port(8081);
        staticFileLocation("/static");

        final ChessController controller = new ChessController();
        get("/", controller::home, new HandlebarsTemplateEngine());
        post("/start", controller::start, new HandlebarsTemplateEngine());
        get("/game/:boardId", controller::game, new HandlebarsTemplateEngine());
        post("/move/:boardId", "application/json", controller::move, new JsonTransformer());
        get("/score/:boardId", controller::score, new JsonTransformer());
    }
}
