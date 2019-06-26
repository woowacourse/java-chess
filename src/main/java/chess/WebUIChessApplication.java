package chess;

import chess.controller.ChessGameController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);

        staticFiles.location("/templates");

        get("/", ChessGameController.serveIndexPage);
        get("/initialize", ChessGameController.initialize);
        get("/loadBoard", ChessGameController.loadBoard);

        post("/move", ChessGameController.move);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
