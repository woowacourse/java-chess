package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.GameDao;
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
        port(8080);
        ChessController controller = new ChessController(new ChessService(new BoardDao(), new GameDao()));

        get("/", (req, res) -> {
            res.redirect("/board/game");
            return null;
        });

        get("/new-board/:gameName", (req, res) ->
        {
            String gameName = req.params(":gameName");
            controller.initGame(gameName);
            res.redirect("/board/"+gameName);
            return null;
        });

        get("/board/:gameName", (req, res) -> {
            BoardDto board = controller.getRunningBoard(req.params(":gameName"));
            if (board == null) {
                res.redirect("/status");
            }
            return render(new ModelAndView(board, "board.html"));
        });

        post("/move/:gameName", (req, res) -> {
            String gameName = req.params(":gameName");
            controller.move(gameName, req.body());
            res.redirect("/board/" + gameName);
            return null;
        });

        get("/status/:gameName", (req, res) -> {
            GameResultDto status = controller.status(req.params(":gameName"));
            controller.end(req.params(":gameName"));
            return render(new ModelAndView(status, "result.html"));
        });

        get("/exception/:message", (req, res) -> {
            String exception = URLDecoder.decode(req.params(":message"), "UTF-8");
            Map<String, Object> model = new HashMap<>();
            model.put("exception", exception);
            return render(new ModelAndView(model, "exception.html"));
        });

        get("/game-end/:gameName", (req, res) -> {
            controller.end(req.params(":gameName"));
            res.redirect("/board/" + req.params(":gameName"));
            return null;
        });

        exception(RuntimeException.class, WebApplication::handle);
    }

    private static String render(ModelAndView modelAndView) {
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    private static void handle(RuntimeException exception, Request request, Response response) {
        try {
            response.redirect("/exception/" + URLEncoder.encode(exception.getMessage(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
