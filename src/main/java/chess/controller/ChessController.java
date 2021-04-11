package chess.controller;

import com.google.gson.Gson;

import chess.service.ChessService;
import spark.Request;
import spark.Response;

public class ChessController {

    private static final Gson GSON = new Gson();

    private final ChessService chessService;

    public ChessController() {
        this.chessService = new ChessService();
    }

    public String newGame(Request req, Response res) {
        Long chessId = chessService.insert();
        res.cookie("chessId", String.valueOf(chessId));
        res.status(201);
        return GSON.toJson(chessId);
    }
}
