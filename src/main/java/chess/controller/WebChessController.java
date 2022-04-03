package chess.controller;

import static spark.Spark.get;

import chess.model.state.Ready;
import chess.model.state.State;
import com.google.gson.Gson;
import java.util.List;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private State state = new Ready();
    private final Gson gson = new Gson();

    public void run() {

        get("/", (req, res) -> {
            return new ModelAndView(state.getBoardForWeb(), "board.html");
        }, new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            this.state = state.proceed(List.of("start"));
            return gson.toJson(state.getBoardForWeb());
        });

        get("/end", (req, res) -> {
            List<String> command = List.of(req.queryParams("command").split(" "));
            this.state = state.proceed(command);
            return new ModelAndView(state.getBoardForWeb(), "board.html");
        }, new HandlebarsTemplateEngine());
    }
}
