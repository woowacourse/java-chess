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

        get("/", (req, res) -> new ModelAndView(gameService.loadBoard(), "board.html")
                , new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
