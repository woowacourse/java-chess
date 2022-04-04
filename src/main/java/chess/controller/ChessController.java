package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.MoveDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private final ChessService chessService;

    public ChessController() {
        chessService = new ChessService();
    }

    private static String render (Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {

        Gson gson = new Gson();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/start", (req, res) -> {
            return gson.toJson(chessService.initializeGame());
        });

        get("/end", (req, res) -> {
            return gson.toJson(chessService.endGame());
        });

        get("/status", (req, res) -> {
            return gson.toJson(chessService.createStatus());
        });

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            moveDto.validateMoveDto();
            return gson.toJson(chessService.move(moveDto));
        });
    }
}
