package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private ChessGame chessGame;

    public void run() {
        port(9090);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lobby.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame = new ChessGame();
            return render(model, "game.html");
        });

        get("/start", (req, res) -> {
           Map<String, Object> model = new HashMap<>();
           chessGame.start();
           model.put("pieces", chessGame.getPieces());
           return render(model, "game.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.end();
            return render(model, "game,html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
