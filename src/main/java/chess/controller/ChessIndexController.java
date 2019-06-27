package chess.controller;

import chess.WebUIChessApplication;
import chess.application.chessround.ChessRoundService;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ChessIndexController {
    public static final String PATH_CHESS_INDEX = "/index";

    public static final Route fetchIndex = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        return WebUIChessApplication.render(model, "index.html");
    };

    public static final Route handleGameType = (req, res) -> {
        String gameType = req.queryParams("game-type");
        if ("new".equals(gameType)) {
            ChessRoundService chessRoundService = ChessRoundService.getInstance();
            chessRoundService.initialize();
        }

        res.redirect("/chess-round");
        return null;
    };

    private ChessIndexController() {
    }
}
