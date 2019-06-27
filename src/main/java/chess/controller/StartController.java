package chess.controller;

import chess.service.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

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

    public Object start(Request req, Response res) throws SQLException {
        GameService gameService = GameService.getInstance();
        return new Gson().toJson(gameService.start());
    }
}
