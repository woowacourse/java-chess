package chess.controller;

import chess.model.dto.MoveDto;
import chess.model.dto.WebBoardDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebController {

    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            WebBoardDto board = chessService.start();
            return gson.toJson(board.getWebBoard());
        });

        post("/move", (req, res) -> {
            MoveDto moveCommand = gson.fromJson(req.body(), MoveDto.class);
            WebBoardDto board = chessService.move(moveCommand);
            return gson.toJson(board.getWebBoard());
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message", "[ERROR] " + exception.getMessage());
            response.body(gson.toJson(jsonObject));
        });
    }


    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
