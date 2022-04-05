package chess.controller;

import chess.domain.piece.ChessPiece;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private ChessService chessService = new ChessService();

    public void run(){
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessService.getCurrentBoard());
            return render(model, "game.html");
        });

        post("/start", (req, res) -> {
            chessService.start();
            res.redirect("/play");
            return null;
        });

        post("/move", (req, res) -> {
            Gson gson = new GsonBuilder().create();
            JsonObject request = gson.fromJson(req.body(), JsonObject.class);
            Map<String, ChessPiece> result = chessService.move(request.get("source").getAsString(), request.get("target").getAsString());
            return gson.toJson(result);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
