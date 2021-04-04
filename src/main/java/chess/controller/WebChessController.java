package chess.controller;

import chess.service.ChessGameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

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

        get("/refreshChessGame", (req, res) -> {
            String id = req.queryParams("id");
            return gson.toJson(chessGameService.refreshChessGame(id));
        });

        get("/loadChessGame", (req, res) -> {
            String id = req.queryParams("id");
            return gson.toJson(chessGameService.loadChessGame(id));
        });

        get("/selectPiece", (req, res) -> {
            String id = req.queryParams("id");
            String selected = req.queryParams("position");
            return gson.toJson(chessGameService.selectPiece(id, selected));
        });

        get("/movePiece", (req, res) -> {
            String id = req.queryParams("id");
            String selected = req.queryParams("selected");
            String target = req.queryParams("target");
            return gson.toJson(chessGameService.moveChessGame(id, selected, target));
        });

        post("/createRoom", (req, res) -> {
            return gson.toJson(chessGameService.createRoom(req.body()));
        });

        get("/enterRoom", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomIdx", req.queryParams("id"));
            return render(model, "game.html");
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
