package chess.controller;

import chess.domain.dto.ResponseDto;
import chess.domain.game.Status;
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

    private final ChessService chessService;

    public WebChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        init();
        play();
        start();
        move();
        status();
        end();
        result();
        save();
    }

    private void init() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });
    }

    private void start() {
        post("/start", (req, res) -> {
            ResponseDto response = chessService.start();
            return convertToJson(response.convertToString());
        });
    }

    private void play() {
        get("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (chessService.checkStatus(Status.END)) {
                res.redirect("/result");
            }
            model.put("play", true);
            model.put("board", chessService.currentBoardForUI());
            return render(model, "game.html");
        });
    }

    private void move() {
        post("/move", (req, res) -> {
            JsonObject request = convertToJson(req.body());
            ResponseDto moveResponse = chessService.move(getSource(request), getTarget(request));
            if (chessService.checkStatus(Status.END)) {
                chessService.end();
            }
            return convertToJson(moveResponse.convertToString());
        });
    }

    private JsonObject convertToJson(String input) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(input, JsonObject.class);
    }

    private String getTarget(JsonObject request) {
        return request.get("target").getAsString();
    }

    private String getSource(JsonObject request) {
        return request.get("source").getAsString();
    }

    private void status() {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (chessService.checkStatus(Status.PLAYING)) {
                model.put("play", true);
                model.put("status", chessService.status());
                model.put("board", chessService.currentBoardForUI());
                return render(model, "game.html");
            }
            res.redirect("/end");
            return null;
        });
    }

    private void save() {
        post("/save", (req, res) -> {
            if (chessService.checkStatus(Status.PLAYING)) {
                chessService.save();
            }
            res.redirect("/play");
            return null;
        });
    }

    private void end() {
        get("/end", (req, res) -> {
            ResponseDto response= chessService.end();
            return convertToJson(response.convertToString());
        });
    }

    private void result() {
        get("/result", (req, res) -> {
            chessService.end();
            Map<String, Object> model = new HashMap<>();
            model.put("play", true);
            model.put("status", chessService.status());
            model.put("board", chessService.currentBoardForUI());
            model.put("winner", chessService.findWinner());
            return render(model, "game.html");
        });
    }
}
