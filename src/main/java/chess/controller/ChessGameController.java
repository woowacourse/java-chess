package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.dto.StatusResponse;
import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.request.PromotionRequest;
import chess.controller.dto.response.GameStatueResponse;
import chess.controller.dto.response.PieceResponse;
import chess.controller.dto.response.ScoreResponse;
import chess.controller.dto.response.WinnerResponse;
import chess.domain.PromotionPiece;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessGameController {

    private final ChessGameService chessGameService;

    public ChessGameController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        Gson gson = new Gson();
        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/board", "application/json", (req, res) -> {
            res.type("application/json");

            return gson.toJson(chessGameService.currentChessBoard()
                    .entrySet()
                    .stream()
                    .map(PieceResponse::from)
                    .collect(Collectors.toList()));
        });

        post("/start", "application/json", (req, res) -> {
            res.type("application/json");

            chessGameService.start();
            return StatusResponse.SUCCESS;
        });

        post("/promotion", "application/json", (req, res) -> {
            res.type("application/json");
            PromotionRequest promotionRequest = gson.fromJson(req.body(), PromotionRequest.class);

            chessGameService.promotion(PromotionPiece.createPromotionPiece(promotionRequest.getPromotionValue()));
            return StatusResponse.SUCCESS;
        });

        post("/move", "application/json", (req, res) -> {
            res.type("application/json");
            MoveRequest moveRequest = gson.fromJson(req.body(), MoveRequest.class);

            chessGameService.move(moveRequest.toSourcePosition(), moveRequest.toTargetPosition());
            return StatusResponse.SUCCESS;
        });

        get("/score", "application/json", (req, res) -> {
            res.type("application/json");

            return gson.toJson(chessGameService.currentScore()
                    .entrySet()
                    .stream()
                    .map(ScoreResponse::from)
                    .collect(Collectors.toList()));
        });

        get("/status", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(new GameStatueResponse(chessGameService.isEndGame()));
        });

        get("/winner", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(WinnerResponse.from(chessGameService.winner()));
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(gson.toJson(Map.of("message", exception.getMessage())));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
