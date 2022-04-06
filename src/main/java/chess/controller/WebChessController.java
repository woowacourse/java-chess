package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;

import chess.dao.GameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final GameService gameService = new GameService();
    private final Gson gson = new Gson();

    public void run() {

        get("/", (req, res) -> new ModelAndView(gameService.loadGameBoard(), "board.html")
                , new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            gameService.startGame();
            System.out.println("제이슨파일이예요!!! " + gson.toJson(gameService.loadGameBoard()));
            return gson.toJson(gameService.loadGameBoard());
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
