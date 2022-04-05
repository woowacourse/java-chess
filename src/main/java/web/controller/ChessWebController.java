package web.controller;

import chess.dto.ErrorMessageDto;
import chess.dto.MoveInfoDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.service.ChessService;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private final ChessService service;

    public ChessWebController() {
        this.service = new ChessService();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        Gson gson = new GsonBuilder().create();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/start", (req, res) -> {
            try {
                return gson.toJson(service.start());
            } catch (RuntimeException e) {
                res.status(503);
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });

        post("/move", (req, res) -> {
            try {
                MoveInfoDto moveInfo = gson.fromJson(req.body(), MoveInfoDto.class);
                return gson.toJson(service.move(moveInfo.getFrom(), moveInfo.getTo()));
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
    }
}
