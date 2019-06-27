package chess.controller;

import chess.model.Square;
import chess.model.dto.MoveResult;
import chess.service.ChessService;
import chess.utils.JsonUtils;
import spark.Route;

public class ChessGameController {
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;

    private ChessService service;

    public ChessGameController(ChessService service) {
        this.service = service;
    }

    public Route serveIndexPage = (req, res) -> {
        res.redirect("/chessgame.html");
        return null;
    };

    public Route move = (req, res) -> {
        MoveResult moveResult;
        res.type("application/json");
        try {
            Square beginSquare = Square.of(req.queryMap("source").value());
            Square endSquare = Square.of(req.queryMap("target").value());
            return service.canMove(beginSquare, endSquare).toJson();
        } catch (Exception e) {
            moveResult = new MoveResult();
            moveResult.setCanMove(false);
            res.status(INTERNAL_SERVER_ERROR_CODE);
            return moveResult.toJson();
        }
    };

    public Route initialize = (req, res) -> {
        res.type("application/json");
        try {
            return service.initializeBoard().toJson();
        } catch (Exception e) {
            res.status(INTERNAL_SERVER_ERROR_CODE);
            return JsonUtils.toJson(e.getMessage());
        }
    };

    public Route loadBoard = (req, res) -> {
        res.type("application/json");
        try {
            return service.createBoardInfo().toJson();
        } catch (Exception e) {
            res.status(INTERNAL_SERVER_ERROR_CODE);
            return JsonUtils.toJson(e.getMessage());
        }
    };
}