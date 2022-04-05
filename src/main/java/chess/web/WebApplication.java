package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.web.controller.ChessController;
import chess.web.dao.RuntimeChessGameDao;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
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

        get("/board", (req, res) -> {
            return render(controller.getBoard());
        });

        post("/move", (req, res) ->
        {
            controller.move(req, res);
            res.redirect("/board");
            return null;
        });

        get("/status", (req, res) -> render(controller.status()));

        exception(RuntimeException.class, WebApplication::handle);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String render(ModelAndView modelAndView) {
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    private static void handle(RuntimeException exception, Request request, Response response) {
        response.body(exception.getMessage());
    }
}
