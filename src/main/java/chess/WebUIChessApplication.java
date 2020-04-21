package chess;

import chess.controller.WebController;
import chess.controller.dto.ResponseDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Gson gson = new Gson();
    private static final WebController webController = new WebController();

    public static void main(String[] args) {
        port(8081);
        staticFiles.location("public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            webController.end();
            return render(model, "main.html");
        });

        get("/loadGame", (req, res) -> {
            try {
                ResponseDto responseDto = webController.loadGame();
                res.status(200);
                return gson.toJson(responseDto);
            } catch (IllegalArgumentException | IllegalStateException e) {
                res.status(400);
                return gson.toJson(e.getMessage());
            }
        });

        post("/move", (req, res) -> {
            try {
                ResponseDto responseDto = webController.moveChessPiece(req);
                res.status(200);
                return gson.toJson(responseDto);
            } catch (IllegalArgumentException | IllegalStateException e) {
                res.status(400);
                return gson.toJson(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}