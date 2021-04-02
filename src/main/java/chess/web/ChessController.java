package chess.web;

import chess.domain.board.ChessBoard;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.dto.MoveRequestDTO;
import chess.dto.ResultDTO;
import chess.dto.board.BoardDTO;
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
        int roomId = parseRoomIdFromUrl(request, response);
        BoardDTO boardDTO = findLatestBoardByRoomId(roomId);
        return GSON.toJsonTree(boardDTO);
    }

    private int parseRoomIdFromUrl(Request request, Response response) {
        response.type(RESPONSE_JSON);
        return Integer.parseInt(request.params(":roomId"));
    }

    private BoardDTO findLatestBoardByRoomId(int roomId) throws SQLException {
        ChessBoard chessBoard = chessService.findLatestBoardByRoomId(roomId);
        TeamType teamType = chessService.findCurrentTeamTypeByRoomId(roomId);
        return BoardDTO.of(chessBoard, teamType);
    }

    public JsonElement move(Request request, Response response) throws SQLException {
        int roomId = parseRoomIdFromUrl(request, response);
        MoveRequestDTO moveRequestDTO = GSON.fromJson(request.body(), MoveRequestDTO.class);
        String current = moveRequestDTO.getCurrent();
        String destination = moveRequestDTO.getDestination();
        String teamType = moveRequestDTO.getTeamType();
        chessService.updateHistory(current, destination, teamType, roomId);
        BoardDTO boardDTO = findLatestBoardByRoomId(roomId);
        return GSON.toJsonTree(boardDTO);
    }

    public JsonElement showResult(Request request, Response response) throws SQLException {
        int roomId = parseRoomIdFromUrl(request, response);
        Result result = chessService.calculateResult(roomId);
        ResultDTO resultDTO = ResultDTO.from(result);
        return GSON.toJsonTree(resultDTO);
    }

    public JsonElement restart(Request request, Response response) throws SQLException {
        int roomId = parseRoomIdFromUrl(request, response);
        chessService.deleteAllHistoriesByRoomId(roomId);
        return GSON.toJsonTree("/");
    }
}
