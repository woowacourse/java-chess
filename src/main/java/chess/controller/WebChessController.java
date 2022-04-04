package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.MoveDto;
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

        get("/", (req, res) -> new ModelAndView(state.getBoardForWeb(), "board.html")
                , new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            this.state = state.proceed(List.of("start"));
            return gson.toJson(state.getBoardForWeb());
        });

        get("/end", (req, res) -> {
            this.state = state.proceed(List.of("end"));
            return gson.toJson(state.getBoardForWeb());
        });

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            this.state = state.proceed(moveDto.getOptions());
            return gson.toJson(state.getBoardForWeb());
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
