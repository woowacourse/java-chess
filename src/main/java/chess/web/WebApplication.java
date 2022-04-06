package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
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
            return render(model, "index.html");
        });

        get("/move", (req, res) -> {
            chessGame.move(req.queryParams("source"), req.queryParams("target"));
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
