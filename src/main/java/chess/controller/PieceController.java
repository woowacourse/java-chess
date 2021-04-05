package chess.controller;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chess.ChessResponse;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDTO;
import chess.domain.piece.PieceDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionDTO;
import chess.domain.service.PieceService;
import spark.Request;
import spark.Response;

public class PieceController {

    private static final Logger log = LoggerFactory.getLogger(PieceController.class);

    private static final Gson GSON = new Gson();

    private final PieceService pieceService;

    public PieceController() {
        this.pieceService = new PieceService();
    }

    public String get(Request req, Response res) throws SQLException {
        log.warn("piece get");
        Long chessId = Long.valueOf(req.params(":chessId"));
        final List<PieceDTO> pieceDTOS = pieceService.get(chessId);
        ChessResponse chessResponse = new ChessResponse.Ok(GSON.toJson(pieceDTOS));
        return GSON.toJson(chessResponse);
    }

    public String insert(Request req, Response res) throws SQLException {
        log.warn("piece insert");
        Long chessId = Long.valueOf(req.params(":chessId"));
        pieceService.insert(chessId);
        ChessResponse chessResponse = new ChessResponse.Created("기물들이 생성되었습니다.");
        return GSON.toJson(chessResponse);
    }

    public String move(Request req, Response res) throws SQLException {
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        MovePositionDTO movePositionDTO = new MovePositionDTO(source, target);
        ChessDTO chessDTO = GSON.fromJson(req.body(), ChessDTO.class);
        Chess chess = new Chess(chessDTO).move(new MovePosition(movePositionDTO));

        Long chessId = Long.valueOf(req.params(":chessId"));
        pieceService.move(chessId, movePositionDTO);

        if (chess.isKindDead()) {
            res.redirect("/");
            return GSON.toJson(new ChessResponse.Ok("왕이 죽었습니다."));
        }
        return GSON.toJson(new ChessResponse.Ok("기물이 이동했습니다."));
    }

    public String delete(Request req, Response res) throws SQLException {
        log.warn("piece delete");
        Long chessId = Long.valueOf(req.params(":chessId"));
        pieceService.delete(chessId);
        ChessResponse chessResponse = new ChessResponse.Ok("기물들을 삭제했습니다.");
        return GSON.toJson(chessResponse);
    }
}
