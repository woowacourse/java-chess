package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.dto.StatusResponse;
import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.request.PromotionRequest;
import chess.controller.dto.response.PieceResponse;
import chess.controller.dto.response.ScoreResponse;
import chess.controller.dto.response.TurnResponse;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameController {

    private final ChessGameService chessGameService;

    public ChessGameController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        Gson gson = new Gson();
        get("/", "application/json", (req, res) -> {
            res.type("application/json");
            Map<Position, Piece> pieces = chessGameService.currentChessBoard();
            return gson.toJson(pieces.entrySet()
                    .stream()
                    .map(PieceResponse::from)
                    .collect(Collectors.toList()));
        });

        post("/start", "application/json", (req, res) -> {
            chessGameService.start();
            return StatusResponse.SUCCESS;
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

        get("/score", "application/json", (req, res) -> {
            res.type("application/json");
            List<ScoreResponse> collect = chessGameService.currentScore()
                    .entrySet()
                    .stream()
                    .map(ScoreResponse::from)
                    .collect(Collectors.toList());
            return gson.toJson(collect);
        });

        get("/status", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(TurnResponse.from(chessGameService.findCurrentTurn()));
        });
    }
}
