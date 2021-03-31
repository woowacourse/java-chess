package chess.web;

import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import chess.dto.ResultDTO;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class ChessController {
    private static final Gson GSON = new Gson();
    private static final String RESPONSE_JSON = "application/json";

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public JsonElement updateChessBoard(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.valueOf(request.params(":roomNumber"));
        BoardDTO boardDTO = chessService.findLatestBoard(roomId);
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement move(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.valueOf(request.params(":roomNumber"));
        RequestDTO requestDTO = GSON.fromJson(request.body(), RequestDTO.class);
        BoardDTO boardDTO = chessService.move(requestDTO, roomId);
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement showResult(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.valueOf(request.params(":roomNumber"));
        ResultDTO resultDTO = chessService.calculateResult(roomId);
        return GSON.toJsonTree(resultDTO);
    }

    public JsonElement restart(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.valueOf(request.params(":roomNumber"));
        chessService.resetDefaultByRoomId(roomId);
        return GSON.toJsonTree("/");
    }
}
