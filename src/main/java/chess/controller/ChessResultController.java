package chess.controller;

import chess.WebUIChessApplication;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ChessResultController {
    public static final String PATH_CHESS_RESULT = "/chess-result";

    public static final Route fetchChessResult = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        String winner = req.queryParams("winner");
        model.put("winner", winner);

        return WebUIChessApplication.render(model, "chess-result.hbs");
    };

    private ChessResultController() {
    }
}
