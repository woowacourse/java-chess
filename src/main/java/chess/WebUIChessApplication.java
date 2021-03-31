package chess;

import chess.controller.WebChessAction;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", WebChessAction.getInstance()::index);

        get("/game", WebChessAction.getInstance()::start);

        post("/move", WebChessAction.getInstance()::move);

        get("/end", WebChessAction.getInstance()::end);

        post("/status", WebChessAction.getInstance()::status);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
