package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.web.dto.BoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame();
        chessGame.start();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", BoardDto.newInstance(chessGame.board()).getPieces());
            model.put("black-score", chessGame.score(Color.WHITE));
            model.put("white-score", chessGame.score(Color.BLACK));
            return render(model, "index.html");
        });

        get("/move", (req, res) -> {
            chessGame.move(req.queryParams("source"), req.queryParams("target"));
            if (chessGame.isFinished()) {
                res.redirect("/winner");
            }
            res.redirect("/");
            return null;
        });

        get("/start", (req, res) -> {
            chessGame.start();
            res.redirect("/");
            return null;
        });

        get("/winner", (req, res) -> {
            if (!chessGame.isFinished()) {
                chessGame.end();
            }
            Map<String, Object> model = new HashMap<>();
            model.put("black-score", chessGame.score(Color.WHITE));
            model.put("white-score", chessGame.score(Color.BLACK));
            model.put("winner", chessGame.result().toString());
            return render(model, "winner.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error-message", exception.getMessage());
            model.put("pieces", BoardDto.newInstance(chessGame.board()).getPieces());
            model.put("black-score", chessGame.score(Color.WHITE));
            model.put("white-score", chessGame.score(Color.BLACK));
            response.body(render(model, "index.html"));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
