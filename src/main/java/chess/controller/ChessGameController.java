package chess.controller;

import chess.service.ChessGameService;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class ChessGameController {
    private static ChessGameService chessGameService = ChessGameService.getInstance();

    public static final Route CREATE_NEW_GAME = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("game", chessGameService.createNewGame());
        return render(model, "chessBoard.html");
    };

    public static final Route CREATE_LATEST_GAME = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("game", chessGameService.createLatestGame());
        return render(model, "chessBoard.html");
    };
}
