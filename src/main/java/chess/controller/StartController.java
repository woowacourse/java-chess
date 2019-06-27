package chess.controller;

import chess.domain.Game;
import chess.dto.BoardDto;
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
        Game game = new Game();
        req.session().attribute("game", game);
        return new Gson().toJson(new BoardDto(game.reload()));
    }
}
