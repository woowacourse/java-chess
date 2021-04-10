package chess;

import chess.controller.web.WebChessController;
import chess.controller.web.dto.ExceptionMessageResponseDto;
import chess.controller.web.dto.game.GameResponseDto;
import chess.controller.web.dto.move.MoveRequestDto;
import chess.controller.web.dto.game.GameRequestDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/static");
        WebChessController webChessController = new WebChessController();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/game", (request, response) -> {
            GameRequestDto gameRequestDto = gson.fromJson(request.body(), GameRequestDto.class);
            Long gameId = webChessController.newGame(gameRequestDto);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("gameId", gameId);
            return jsonObject;
        }, gson::toJson);

        get("/game/:id", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            GameResponseDto gameResponseDto = webChessController.findGameByGameId(gameId);
            Map<String, Object> model = new HashMap<>();
            model.put("gameId", gameId);
            model.put("whiteUsername", gameResponseDto.getWhiteUsername());
            model.put("blackUsername", gameResponseDto.getBlackUsername());
            model.put("roomName", gameResponseDto.getRoomName());
            return render(model, "board.html");
        });

        get("/game/:id/load", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            return webChessController.findPiecesByGameId(gameId);
        }, gson::toJson);

        get("/game/:id/score", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            return webChessController.findScoreByGameId(gameId);
        }, gson::toJson);

        get("/game/:id/state", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            return webChessController.findStateByGameId(gameId);
        }, gson::toJson);

        get("/game/:id/path", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            String source = request.queryParams("source");
            return webChessController.movablePath(source, gameId).getPath();
        }, gson::toJson);

        get("/game/:id/history", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            return webChessController.findHistoryByGameId(gameId);
        }, gson::toJson);

        post("/game/:id/move", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            MoveRequestDto moveRequestDto = gson.fromJson(request.body(), MoveRequestDto.class);
            return webChessController.move(moveRequestDto, gameId);
        }, gson::toJson);

        exception(RuntimeException.class, (exception, request, response) -> {
            response.type("application/json; charset=utf-8");
            response.status(400);
            response.body(gson.toJson(new ExceptionMessageResponseDto(exception.getMessage())));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
