package chess.controller;

import chess.service.ChessGameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebChessController {
    private Gson gson = null;
    private final ChessGameService chessGameService;

    public WebChessController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/startChessGame", (req, res) -> {
            return gson.toJson(chessGameService.createChessGame());
        });

        get("/loadChessGame", (req, res) -> {
            return gson.toJson(chessGameService.loadChessGame("1"));
        });

        get("/endChessGame", (req, res) -> {
            return gson.toJson(chessGameService.endChessGame("1"));
        });

        get("/selectPiece", (req, res) -> {
            String selected = req.queryParams("position");
            return gson.toJson(chessGameService.selectPiece("1", selected));
        });

        get("/movePiece", (req, res) -> {
            String selected = req.queryParams("selected");
            String target = req.queryParams("target");
            return gson.toJson(chessGameService.moveChessGame("1", selected, target));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
