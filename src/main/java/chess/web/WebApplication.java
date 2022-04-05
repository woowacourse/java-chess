package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;
import static spark.Spark.staticFileLocation;

import chess.web.controller.ChessController;
import chess.web.dao.RuntimeChessGameDao;
import chess.web.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        ChessController controller = new ChessController(new ChessService(new RuntimeChessGameDao()));

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/new-board", (req, res) ->
        {
            controller.initGame();
            res.redirect("/board");
            return null;
        });

        get("/board", (req, res) -> render(controller.getBoard()));

        post("/move", (req, res) ->
        {
            controller.move(req, res);
            res.redirect("/board");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String render(ModelAndView modelAndView) {
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
}
