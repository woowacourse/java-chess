package chess.controller.web;

import chess.controller.web.dto.MoveRequestDto;
import chess.controller.web.dto.MoveResponseDto;
import chess.controller.web.dto.StartResponseDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final int BAD_REQUEST = 500;

    public static void start() {
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        path("/game", () -> {
            get("/start", (request, response) -> {
                chessService.start();
                response.type("application/json");
                return gson.toJson(new StartResponseDto(true));
            });

            post("/move", (request, response) -> {
                MoveRequestDto moveRequestDto = gson.fromJson(request.body(), MoveRequestDto.class);
                chessService.move(moveRequestDto.getFromPosition(), moveRequestDto.getToPosition());
                response.type("application/json");
                return gson.toJson(new MoveResponseDto(true));
            });
        });

        exception(IllegalArgumentException.class, (e, request, response) -> {
            response.status(BAD_REQUEST);
            response.body(e.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
