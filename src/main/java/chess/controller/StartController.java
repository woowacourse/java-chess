package chess.controller;

import chess.domain.Game;
import chess.dto.BoardDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class StartController {
    public static final String PATH = "/start";

    public Object start(Request req, Response res) throws SQLException {
        Game game = new Game();
        req.session().attribute("game", game);
        return new Gson().toJson(new BoardDto(game.reload()));
    }
}
