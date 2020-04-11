package chess.controller;

import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void play() {
        //start
        get("/start", (req, res) -> {
            chessService.start();
            return render(chessService.makeStartResponse(), "chessGameStart.html");
        });

        //play last game
        get("/playing/lastGame", (req, res) -> {
            chessService.playLastGame();
            return render(chessService.makeMoveResponse(), "chessGame.html");
        });

        //play new game
        get("/playing/newGame", (req, res) -> {
            chessService.playNewGame();
            return render(chessService.makeMoveResponse(), "chessGame.html");
        });

        //move source target
        post("/playing/move", (req, res) -> {
            String source = req.headers("source");
            String target = req.headers("target");
            chessService.move(source, target);

            return GSON.toJson(chessService.makeMoveResponse());
        });

        //end
        get("/end", (req, res) -> {
            chessService.end();
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessGameEnd.html");
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
