package chess.controller;

import java.sql.SQLException;

import com.google.gson.Gson;

import chess.ChessResponse;
import chess.service.ChessService;
import spark.Request;
import spark.Response;

public class ChessController {

    private static final Gson GSON = new Gson();

    private final ChessService chessService;

    public ChessController() {
        this.chessService = new ChessService();
    }

    public String getIds(Request req, Response res) throws SQLException {
        String chessId = chessService.findChessIdAsString();
        ChessResponse chessResponse = new ChessResponse.Ok(chessId);
        return GSON.toJson(chessResponse);
    }

    public String getTurn(Request req, Response res) throws SQLException {
        Long chessId = Long.valueOf(req.params(":chessId"));
        String turn = chessService.findTurnById(chessId);
        ChessResponse chessResponse = new ChessResponse.Ok(turn);
        return GSON.toJson(chessResponse);
    }

    public String insert(Request req, Response res) throws SQLException {
        chessService.insert();

        Long chessId = chessService.findChessId();
        res.cookie("chessId", String.valueOf(chessId));

        ChessResponse chessResponse = new ChessResponse.Created("새로운 체스가 추가되었습니다.");
        return GSON.toJson(chessResponse);
    }

    public String updateTurn(Request req, Response res) throws SQLException {
        Long chessId = Long.valueOf(req.params(":chessId"));
        chessService.updateTurn(chessId);
        ChessResponse chessResponse = new ChessResponse.Ok("턴이 변경되었습니다.");
        return GSON.toJson(chessResponse);
    }

    public String delete(Request req, Response res) throws SQLException {
        Long chessId = Long.valueOf(req.params(":chessId"));
        chessService.delete(chessId);
        ChessResponse chessResponse = new ChessResponse.Ok("저장된 체스를 삭제했습니다.");
        return GSON.toJson(chessResponse);
    }
}
