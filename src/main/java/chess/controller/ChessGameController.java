package chess.controller;

import chess.model.Square;
import chess.model.dto.MoveResult;
import chess.service.ChessService;
import chess.utils.JsonUtils;
import spark.Route;

public class ChessGameController {
    private static ChessService service;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;

    static {
        service = new ChessService();
    }

    public static Route serveIndexPage = (request, response) -> {
        response.redirect("/chessgame.html");
        return null;
    };

    public static Route move = (request, response) -> {
        MoveResult moveResult;
        response.type("application/json");
        try {
            Square beginSquare = Square.of(request.queryMap("source").value());
            Square endSquare = Square.of(request.queryMap("target").value());
            return service.canMove(beginSquare, endSquare).toJson();
        } catch (Exception e) {
            moveResult = new MoveResult();
            moveResult.setCanMove(false);
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return moveResult.toJson();
        }
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        try {
            return service.initializeBoard().toJson();
        } catch (Exception e) {
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return JsonUtils.toJson(e.getMessage());
        }
    };

    public static Route loadBoard = (request, response) -> {
        response.type("application/json");
        try {
            return service.createBoardInfo().toJson();
        } catch (Exception e) {
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return JsonUtils.toJson(e.getMessage());
        }
    };
}