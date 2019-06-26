package chess.controller;

import chess.model.ChessEngine;
import chess.service.ScoreService;
import spark.Request;
import spark.Response;

public class ScoreController {
    public static final String URL = "/score/:gameId";

    private static final ScoreController INSTANCE = new ScoreController();

    public static ScoreController getInstance() {
        return INSTANCE;
    }

    public String get(final Request req, final Response res) {
        ChessEngine chessEngine = req.session().attribute("engine");
        ScoreService scoreService = ScoreService.getInstance();
        return scoreService.getResult(chessEngine);
    }
}
