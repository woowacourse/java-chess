package web.controller;

import web.dto.ErrorMessageDto;
import web.dto.GameInfoDto;
import web.dto.MoveInfoDto;
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

    private final ChessService service;
    private final Gson gson = new GsonBuilder().create();

    public ChessWebController() {
        this.service = new ChessService(new BoardDao(), new GameDao());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        post("/start/newGame", (req, res) -> {
            try {
                GameInfoDto gameDto = gson.fromJson(req.body(), GameInfoDto.class);
                return gson.toJson(service.startNewGame(gameDto));
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        post("/start/resumeGame", (req, res) -> {
            try {
                GameInfoDto gameDto = gson.fromJson(req.body(), GameInfoDto.class);
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

        get("/status/:roomName", (req, res) -> {
            try {
                GameInfoDto gameInfoDto = new GameInfoDto(req.params(":roomName"));
                return gson.toJson(service.status(gameInfoDto));
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        get("/finish", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        post("/finish", (req, res) -> {
            try {
                GameInfoDto gameDto = gson.fromJson(req.body(), GameInfoDto.class);
                service.deleteAndFinish(gameDto);
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
            return null;
        });
    }
}
