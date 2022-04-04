package chess;

import chess.controller.Command;
import chess.domain.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFiles.location("/public");
        ChessGame chessGame = ChessGame.create();

        get("/welcome", (req, res) -> {
            Command command = Command.of(req.queryParams("command"));
            if (Command.START.equals(command)) {
                chessGame.initialze();
                res.redirect("/board");
            }
            if (Command.END.equals(command)) {
                stop();
            }
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
