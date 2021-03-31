package chess;

import chess.controller.WebChessAction;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        WebChessAction webChessAction = new WebChessAction();

        get("/", webChessAction::index);

        get("/game", webChessAction::start);

        post("/move", webChessAction::move);

        get("/end", webChessAction::end);

        post("/status", webChessAction::status);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
