package chess.controller;

import java.util.List;

import com.google.gson.Gson;

import chess.domain.board.Board;
import chess.domain.board.ScoreDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDTO;
import chess.domain.piece.PieceDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionDTO;
import chess.service.PieceService;
import spark.Request;
import spark.Response;

public class PieceController {

    private static final Gson GSON = new Gson();

    private final PieceService pieceService;

    public PieceController() {
        this.pieceService = new PieceService();
    }

    public String get(Request req, Response res) {
        Long chessId = Long.valueOf(req.params(":chessId"));
        final List<PieceDTO> pieceDTOS = pieceService.get(chessId);
        return GSON.toJson(pieceDTOS);
    }

    public String getScore(Request req, Response res) {
        Long chessId = Long.valueOf(req.params(":chessId"));
        final List<PieceDTO> pieceDTOS = pieceService.get(chessId);
        Board board = Board.from(pieceDTOS);
        ScoreDTO scoreDTO = new ScoreDTO(board);
        return GSON.toJson(scoreDTO);
    }

    public String insert(Request req, Response res) {
        Long chessId = Long.valueOf(req.params(":chessId"));
        List<PieceDTO> pieceDTOS = pieceService.insert(chessId);
        res.status(201);

        return GSON.toJson(pieceDTOS);
    }

    public Response move(Request req, Response res) {
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        MovePositionDTO movePositionDTO = new MovePositionDTO(source, target);
        ChessDTO chessDTO = GSON.fromJson(req.body(), ChessDTO.class);
        Chess chess = new Chess(chessDTO).move(new MovePosition(movePositionDTO));

        Long chessId = Long.valueOf(req.params(":chessId"));
        pieceService.move(chessId, movePositionDTO);

        res.status(204);
        return res;
    }

    public Response delete(Request req, Response res) {
        Long chessId = Long.valueOf(req.params(":chessId"));
        pieceService.delete(chessId);
        res.status(204);
        return res;
    }
}
