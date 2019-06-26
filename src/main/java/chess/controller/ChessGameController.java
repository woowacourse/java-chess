package chess.controller;

import chess.model.Square;
import chess.model.dto.MoveResult;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.Route;

public class ChessGameController {
    private static ChessService service;

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
            return new Gson().toJson(service.canMove(beginSquare, endSquare));
        } catch (Exception e) {
            moveResult = new MoveResult();
            moveResult.setCanMove(false);
            response.status(500);
            return new Gson().toJson(moveResult);
        }
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        try {
            return new Gson().toJson(service.initializeBoard());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    public static Route loadBoard = (request, response) -> {
        response.type("application/json");
        try {
            return new Gson().toJson(service.createBoardInfo());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };
}