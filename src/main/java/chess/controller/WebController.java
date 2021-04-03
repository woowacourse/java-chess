package chess.controller;

import chess.domain.ChessGameManager;
import chess.dto.ChessBoardDto;
import com.google.gson.Gson;

import static spark.Spark.get;

public class WebController {
    private final Gson gson = new Gson();
    private final ChessGameManager chessGameManager;

    public WebController(ChessGameManager chessGameManager) {
        this.chessGameManager = chessGameManager;
    }

    public void run() {
        get("/newgame", (req, res) -> {
            chessGameManager.start();
            return gson.toJson(ChessBoardDto.from(chessGameManager.getBoard()));
        });
    }
}
