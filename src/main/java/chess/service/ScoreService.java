package chess.service;

import chess.model.ChessEngine;
import chess.model.GameFlow;
import chess.model.GameResult;
import com.google.gson.Gson;

public class ScoreService {
    private static final ScoreService INSTANCE = new ScoreService();

    private ScoreService() {
    }

    public static ScoreService getInstance() {
        return INSTANCE;
    }

    public String getResult(final ChessEngine chessEngine) {
        GameResult result = chessEngine.getGameStatus();
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}
