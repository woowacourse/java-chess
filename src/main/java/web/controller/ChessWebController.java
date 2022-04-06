package web.controller;

import chess.dto.ErrorMessageDto;
import chess.dto.GameDto;
import chess.dto.MoveInfoDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dao.BoardDao;
import web.dao.GameDao;
import web.service.ChessService;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private ChessService service;

    public ChessWebController() {
        this.service = new ChessService(new BoardDao(), new GameDao());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        Gson gson = new GsonBuilder().create();

        get("/", (req, res) -> {
            this.service = new ChessService(new BoardDao(), new GameDao());
            return render(new HashMap<>(), "index.html");
        });

        post("/start/newGame", (req, res) -> {
            try {
                GameDto gameDto = gson.fromJson(req.body(), GameDto.class);
                return gson.toJson(service.startNewGame(gameDto));
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        post("/start/resumeGame", (req, res) -> {
            try {
                GameDto gameDto = gson.fromJson(req.body(), GameDto.class);
                return gson.toJson(service.resumeGame(gameDto));
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        post("/move", (req, res) -> {
            try {
                MoveInfoDto moveInfo = gson.fromJson(req.body(), MoveInfoDto.class);
                return gson.toJson(service.move(moveInfo));
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        get("/status", (req, res) -> {
            try {
                return gson.toJson(service.status());
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        get("/finish", (req, res) -> {
            try {
                service.finish();
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
            return null;
        });

        post("/finish", (req, res) -> {
            try {
                GameDto gameDto = gson.fromJson(req.body(), GameDto.class);
                service.deleteAndFinish(gameDto);
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
            return null;
        });
    }
}
