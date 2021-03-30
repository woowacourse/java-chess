package chess.controller;

import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void play(){
        get("/play", (req, res) -> {
            chessService.start();
            return render(chessService.startResponse(), "chessStart.html");
        });

        get("/play/new", (req, res) -> {
            chessService.playNewGame();
            return render(chessService.initResponse(), "chessGame.html");
        });

        post("/play/move", (req, res) -> {
            String source = req.queryParams("source"); //req.headers("source");
            String target = req.queryParams("target"); //req.headers("target");
            try {
                chessService.move(source, target);
                return GSON.toJson(chessService.moveResponse());
            } catch (IllegalArgumentException e) {
                res.status(400);
                return e.getMessage();
            }
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
