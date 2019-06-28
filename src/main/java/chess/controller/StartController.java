package chess.controller;

import chess.dto.BoardDto;
import chess.service.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class StartController {
    public static final String PATH = "/start";

    private StartController() {
    }

    public static StartController getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static StartController INSTANCE = new StartController();
    }

    public Object start(Request req, Response res) {
        GameService gameService = GameService.getInstance();
        BoardDto boardDto = gameService.start();
        return new Gson().toJson(boardDto);
    }
}
