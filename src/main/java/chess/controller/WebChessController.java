package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;

import chess.dao.StateService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final StateService stateService = new StateService();
    private final Gson gson = new Gson();

    public void run() {

        get("/", (req, res) -> new ModelAndView(stateService.loadBoard(), "board.html")
                , new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
