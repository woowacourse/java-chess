package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.RuntimeChessGameDao;
import chess.service.BoardDto;
import chess.service.ChessService;
import chess.service.GameResultDto;
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
            BoardDto board = controller.getRunningBoard();
            if (board == null) {
                res.redirect("/status");
            }
            return render(new ModelAndView(board, "board.html"));
        });

        post("/move", (req, res) -> {
            controller.move(req.body());
            res.redirect("/board");
            return null;
        });

        get("/status", (req, res) -> {
            GameResultDto status = controller.status();
            return render(new ModelAndView(status, "result.html"));
        });

        get("/exception", (req, res) -> {
            String exception = URLDecoder.decode(req.cookie("exception"), "UTF-8");
            Map<String, Object> model = new HashMap<>();
            model.put("exception", exception);
            return render(new ModelAndView(model, "exception.html"));
        });

        get("/game-end", (req, res) -> {
           controller.end();
           res.redirect("/board");
           return null;
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
