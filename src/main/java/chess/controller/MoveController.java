package chess.controller;

import chess.domain.Game;
import chess.dto.BoardDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class MoveController {
    public Object move(Request req, Response res) throws SQLException {
        Game game = req.session().attribute("game");
        int from = Integer.parseInt(req.queryParams("from"));
        int to = Integer.parseInt(req.queryParams("to"));
        return new Gson().toJson(new BoardDto(game.play(from, to)));
    }
}
