package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.model.dao.RuntimeChessGameDao;
import chess.service.ChessService;
import chess.web.controller.ChessController;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) throws UnsupportedEncodingException {
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
            ModelAndView modelAndView = controller.getBoard();
            if (modelAndView == null) {
                res.redirect("/status");
            }
            return render(controller.getBoard());
        });

        post("/move", (req, res) ->
        {
            controller.move(req, res);
            res.redirect("/board");
            return null;
        });

        get("/status", (req, res) -> render(controller.status()));

        get("/exception", (req, res) -> {
            String exception = URLDecoder.decode(req.cookie("exception"), "UTF-8");
            Map<String, Object> model = new HashMap<>();
            model.put("exception", exception);
            return render(new ModelAndView(model, "exception.html"));
        });

        exception(RuntimeException.class, WebApplication::handle);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String render(ModelAndView modelAndView) {
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    private static void handle(RuntimeException exception, Request request, Response response) {
        try {
            response.cookie("exception", URLEncoder.encode(exception.getMessage(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.redirect("/exception");
    }
}
