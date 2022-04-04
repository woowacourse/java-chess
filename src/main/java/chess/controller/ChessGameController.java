package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.dto.StatusResponse;
import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.request.PromotionRequest;
import chess.controller.dto.response.ChessGameResponse;
import chess.domain.Position;
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

        post("/move", "application/json", (req, res) -> {
            MoveRequest moveRequest = gson.fromJson(req.body(), MoveRequest.class);
            Position source = moveRequest.toSourcePosition();
            Position target = moveRequest.toTargetPosition();
            chessGameService.move(source, target);
            return StatusResponse.SUCCESS;
        });
    }
}
