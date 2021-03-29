package chess.controller;

import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebController {

    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void play(){
        get("/play", (req, res) -> {
            chessService.start();
            return render(chessService.startRequest(), "chessStart.html");
        });

        get("/play/continue", (req, res) -> {
            chessService.continuedGame();
            return render(chessService.moveRequest(), "chessGame.html");
        });

        get("/play/new", (req, res) -> {
            chessService.playNewGame();
            return render(chessService.initRequest(), "chessGame.html");
        });

        get("/play/end", (req, res) -> {
            chessService.end();
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessEnd.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
