package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.MoveDto;
import chess.service.GameService;
import com.google.gson.Gson;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final GameService gameService;
    private final Gson gson;

    public WebChessController(GameService gameService) {
        this.gameService = gameService;
        this.gson = new Gson();
    }

    public void run() {

        get("/", (req, res) -> new ModelAndView(new HashMap<>(), "board.html")
                , new HandlebarsTemplateEngine());

        post("/start", (req, res) -> gson.toJson(gameService.start()));

        post("/end", (req, res) -> gson.toJson(gameService.end()));

        get("/status", (req, res) -> gson.toJson(gameService.status()));

        get("/load", (req, res) -> gson.toJson(gameService.load()));

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            return gson.toJson(gameService.move(moveDto));
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
