package chess.controller.web;

import chess.controller.web.dto.BasicResponseDto;
import chess.controller.web.dto.MoveRequestDto;
import chess.controller.web.dto.MoveResponseDto;
import chess.controller.web.dto.StartResponseDto;
import chess.controller.web.dto.WebResponseDto;
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
    private static final int HTTP_STATUS_OK = 200;
    public static final String EMPTY_ERROR_MSG = "";

    public static void start() {
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        gameRouting(chessService);

        exceptionHandling();
    }

    private static void gameRouting(ChessService chessService) {
        path("/game", () -> {
            get("/start", (request, response) -> {
                chessService.start();
                response.type("application/json; charset=utf-8");
                return BasicResponseDto.createSuccessResponseDto(new StartResponseDto(chessService.nextColor(), chessService.getPieces()));
            }, gson::toJson);

            post("/move", (request, response) -> {
                MoveRequestDto moveRequestDto = gson.fromJson(request.body(), MoveRequestDto.class);
                chessService.move(moveRequestDto.getFromPosition(), moveRequestDto.getToPosition());
                response.type("application/json; charset=utf-8");
                return BasicResponseDto.createSuccessResponseDto(new MoveResponseDto(chessService.isEnd(), chessService.nextColor()));
            }, gson::toJson);
        });
    }

    private static void exceptionHandling() {
        exception(RuntimeException.class, (e, request, response) -> {
            response.status(HTTP_STATUS_OK);
            response.type("application/json; charset=utf-8");
            response.body(gson.toJson(new BasicResponseDto(true, e.getMessage(), new WebResponseDto() {
            })));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
