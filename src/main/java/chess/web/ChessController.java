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
        BoardDTO boardDTO = chessService.findLatestBoard();
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement move(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        RequestDTO requestDTO = GSON.fromJson(request.body(), RequestDTO.class);
        BoardDTO boardDTO = chessService.move(requestDTO);
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement showResult(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        ResultDTO resultDTO = chessService.calculateResult();
        return GSON.toJsonTree(resultDTO);
    }

    public JsonElement restart(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        chessService.resetDefault();
        return GSON.toJsonTree("/");
    }
}
