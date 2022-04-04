package chess.controller;

import static spark.Spark.get;

import chess.controller.dto.ChessGameResponse;
import chess.service.ChessGameService;
import com.google.gson.Gson;

public class ChessGameController {

    private final ChessGameService chessGameService;

    public ChessGameController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        Gson gson = new Gson();
        get("/", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(ChessGameResponse.from(chessGameService.findGameTurn()));
        });
    }
}
