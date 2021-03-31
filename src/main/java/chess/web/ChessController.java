package chess.web;

import chess.dto.BoardDTO;
import chess.dto.MoveRequestDTO;
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

    public JsonElement showChessBoard(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.parseInt(request.params(":roomId"));
        BoardDTO boardDTO = chessService.findLatestBoardByRoomId(roomId);
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement move(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.parseInt(request.params(":roomId"));
        MoveRequestDTO moveRequestDTO = GSON.fromJson(request.body(), MoveRequestDTO.class);
        BoardDTO boardDTO = chessService.move(moveRequestDTO, roomId);
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement showResult(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.parseInt(request.params(":roomNumber"));
        ResultDTO resultDTO = chessService.calculateResult(roomId);
        return GSON.toJsonTree(resultDTO);
    }

    public JsonElement restart(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        int roomId = Integer.parseInt(request.params(":roomNumber"));
        chessService.resetDefaultByRoomId(roomId);
        return GSON.toJsonTree("/");
    }
}
