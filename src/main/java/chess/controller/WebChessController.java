package chess.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.domain.game.GameState;
import chess.dto.Arguments;
import chess.dto.BoardResponse;
import chess.service.GameService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private static final Gson GSON = new Gson();

    private final GameService gameService;

    public WebChessController(GameService gameService) {
        this.gameService = gameService;
    }

    public String index(Request request, Response response) {
        return render(new HashMap<>(), "index.html");
    }

    public String main(Request request, Response response) {
        String roomName = request.queryParams("room_name");
        GameState state = gameService.readGameState(roomName);

        return render(Map.of(
            "board", state.getPointPieces(),
            "color", state.getColor(),
            "state", state.getState()), "game.html");
    }

    public String create(Request request, Response response) {
        String roomName = request.queryParams("room_name");
        gameService.createNewGame(roomName);
        return GSON.toJson(Map.of("url", "/main?room_name=" + roomName));
    }

    public String enter(Request request, Response response) {
        String roomName = request.queryParams("room_name");
        return GSON.toJson(Map.of("url", "/main?room_name=" + roomName));
    }

    public String start(Request request, Response response) {
        String roomName = request.queryParams("room_name");
        gameService.startGame(roomName);
        return GSON.toJson(Map.of("url", "/main?room_name=" + roomName));
    }

    public String end(Request request, Response response) {
        String roomName = request.queryParams("room_name");
        gameService.finishGame(roomName);
        return GSON.toJson(Map.of("url", "/"));
    }

    public String move(Request request, Response response) {
        String roomName = request.queryParams("room_name");

        GameState state = gameService.moveBoard(
            roomName,
            Arguments.ofRequest(request, Command.MOVE.getParameters())
        );
        return GSON.toJson(Map.of(
            "board", BoardResponse.of(state.getPointPieces()),
            "color", state.getColor(),
            "state", state.getState())
        );
    }

    public String status(Request request, Response response) {
        String roomName = request.queryParams("room_name");
        GameState state = gameService.readGameState(roomName);
        return GSON.toJson(Map.of("board", BoardResponse.of(state.getPointPieces()),
            "score", state.getColorScore()));
    }

    public void handleException(Exception exception, Request request, Response response) {
        exception.printStackTrace();
        response.status(500);
        response.body(GSON.toJson(Map.of("exception", exception.getMessage())));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
