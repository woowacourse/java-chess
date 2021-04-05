package chess.controller;

import java.sql.SQLException;

import com.google.gson.Gson;

import chess.ChessResponse;
import chess.domain.service.ChessService;
import spark.Request;

public class ChessController {

    public static final Gson GSON = new Gson();

    private final ChessService chessService;

    public ChessController() {
        this.chessService = new ChessService();
    }

    public String insert(Request req, spark.Response res) throws SQLException {
        chessService.insert();

        Long chessId = chessService.findChessId();
        res.cookie("chessId", String.valueOf(chessId));
        res.redirect("/chess");

        ChessResponse chessResponse = new ChessResponse.Created("새로운 체스가 추가되었습니다.");
        return GSON.toJson(chessResponse);
    }

    public String update(Request req, spark.Response res) throws SQLException {
        chessService.updateTurn();
        ChessResponse chessResponse = new ChessResponse.Ok("턴이 변경되었습니다.");
        return GSON.toJson(chessResponse);
    }

    public String delete(Request req, spark.Response res) throws SQLException {
        chessService.delete();
        ChessResponse chessResponse = new ChessResponse.Ok( "저장된 체스를 삭제했습니다.");
        return GSON.toJson(chessResponse);
    }
}
