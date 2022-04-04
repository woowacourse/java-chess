package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.dto.ChessGameResponse;
import chess.controller.dto.PromotionRequest;
import chess.controller.dto.StatusResponse;
import chess.domain.PromotionPiece;
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

        post("/promotion", "application/json", (req, res) -> {
            PromotionRequest promotionRequest = gson.fromJson(req.body(), PromotionRequest.class);
            PromotionPiece promotionPiece = PromotionPiece.createPromotionPiece(promotionRequest.getPromotionValue());
            chessGameService.promotion(promotionPiece);
            return StatusResponse.SUCCESS;
        });
    }
}
