package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.web.utils.JsonConverter;
import chess.dto.piece.PieceDto;
import chess.service.PieceService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceController {

    private final PieceService pieceService;

    public PieceController(final PieceService pieceService) {
        this.pieceService = pieceService;
        addResponsePath();
    }

    private void addResponsePath() {
        post("/api/pieces/new", (req, res) -> {
            final Map<String, String> params = JsonConverter.fromJson(req.body(), HashMap.class);
            final long gameId = Long.parseLong(params.get("gameId"));
            pieceService.initPieces(gameId);
            return Collections.emptyMap();
        }, JsonConverter::toJson);

        get("/api/pieces/:gameId", (req, res) -> {
            final long gameId = Long.parseLong(req.params("gameId"));
            List<PieceDto> pieceDtos = pieceService.findAll(gameId);
            return pieceDtos;
        }, JsonConverter::toJson);
    }
}
