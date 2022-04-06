package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.GameService;
import chess.dto.MoveDto;
import com.google.gson.Gson;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final GameService gameService = new GameService();
    private final Gson gson = new Gson();

    public void run() {

        get("/", (req, res) -> new ModelAndView(new HashMap<>(), "board.html")
                , new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            gameService.startGame();
            return gson.toJson(gameService.loadGameBoard());
        });

        get("/end", (req, res) -> {
            gameService.endGame();
            return gson.toJson(gameService.loadGameBoard());
        });

        get("/load", (req, res) -> gson.toJson(gameService.loadGameBoard()));

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            gameService.moveGamePiece(moveDto.getCommand());
            return gameService.loadGameBoard();
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
