package chess.controller;

import chess.domain.Game;
import spark.Request;
import spark.Response;

public class ScoreController {
    public static final String PATH = "/score";

    private ScoreController() {
    }

    public static ScoreController getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static ScoreController INSTANCE = new ScoreController();
    }

    public Object score(Request req, Response res) {
        Game game = req.session().attribute("game");
        return game.getStatusBoard().toString();
    }
}
