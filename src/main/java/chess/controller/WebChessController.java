package chess.controller;

import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {

    private ChessService chessService = new ChessService();

    public void run() {
        init();
        play();
        start();
        move();
        status();
        end();
        result();
    }

    private void end() {
        get("/end", (req, res) -> {
            chessService.end();
            res.redirect("/");
            return null;
        });
    }

    private void result() {
        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("now", chessService.status());
            model.put("board", chessService.getCurrentBoard());
            model.put("winner", chessService.findWinner());
            return render(model, "game.html");
        });
    }

    private void init() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });
    }

    private void play() {
        get("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (chessService.isEnd()) {
                res.redirect("/result");
            }
            model.put("board", chessService.getCurrentBoard());
            return render(model, "game.html");
        });
    }

    private void start() {
        post("/start", (req, res) -> {
            chessService.start();
            res.redirect("/play");
            return null;
        });
    }

    private void move() {
        post("/move", (req, res) -> {
            Gson gson = new GsonBuilder().create();
            JsonObject request = gson.fromJson(req.body(), JsonObject.class);
            String move = chessService.move(request.get("source").getAsString(), request.get("target").getAsString());
            if (chessService.isEnd()) {
                chessService.end();
            }
            return gson.fromJson(move, JsonObject.class);
        });
    }

    private void status() {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("now", chessService.status());
            model.put("board", chessService.getCurrentBoard());
            return render(model, "game.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
