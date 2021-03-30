package chess.controller;

import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import chess.dto.ResultDTO;
import chess.repository.ChessRepository;
import chess.repository.ConnectionManager;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class ChessController {
    private static final Gson GSON = new Gson();
    private static final String RESPONSE_JSON = "application/json";

    private static ChessService chessService = new ChessService(new ChessRepository(ConnectionManager.makeConnection()));

    private ChessController() {
    }

    public static JsonElement updateChessBoard(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        BoardDTO boardDTO = chessService.get();
        return GSON.toJsonTree(boardDTO);
    }

    public static JsonElement move(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        RequestDTO requestDTO = GSON.fromJson(request.body(), RequestDTO.class);
        BoardDTO boardDTO = chessService.move(requestDTO);
        return GSON.toJsonTree(boardDTO);
    }

    public static JsonElement showResult(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        ResultDTO resultDTO = chessService.calculateResult();
        return GSON.toJsonTree(resultDTO);
    }

    public static JsonElement restart(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        chessService.resetDefault();
        return GSON.toJsonTree("/");
    }
}
