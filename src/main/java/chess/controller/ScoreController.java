package chess.controller;

import chess.domain.Game;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class ScoreController {
    public Object score(Request req, Response res) throws SQLException {
        Game game = req.session().attribute("game");
        return game.getStatusBoard().toString();
    }
}
