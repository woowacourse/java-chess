package chess.controller;

import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.piece.Piece;
import chess.dto.PieceDTO;
import chess.dto.ResultDTO;
import chess.dto.TurnDTO;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private static final int HTTP_STATUS_SUCCESSFUL = 200;

    private final Map<String, Object> model;
    private final int httpStatus;

    public Response() {
        this(HTTP_STATUS_SUCCESSFUL);
    }

    public Response(int httpStatus) {
        this.model = new HashMap<>();
        this.httpStatus = httpStatus;
    }

    public Response(ChessGame chessGame) {
        this.model = pieceMoveModelToRender(chessGame);
        this.httpStatus = HTTP_STATUS_SUCCESSFUL;
    }

    public Response(ChessGame chessGame, String roomId) {
        this.model = createRoomModelToRender(chessGame, roomId);
        this.httpStatus = HTTP_STATUS_SUCCESSFUL;
    }

    private Map<String, Object> createRoomModelToRender(ChessGame chessGame, String roomId) {
        Map<String, Object> model = pieceMoveModelToRender(chessGame);
        model.put("room_id", roomId);
        return model;
    }

    private Map<String, Object> pieceMoveModelToRender(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        Color turn = chessGame.getTurn();
        model.put("turn", new TurnDTO(turn));

        Map<Position, Piece> chessBoard = chessGame.getChessBoardAsMap();
        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            model.put(entry.getKey().getPosition(), new PieceDTO(entry.getValue()));
        }

        Result result = chessGame.calculateResult();
        model.put("result", new ResultDTO(result));
        return model;
    }

    public boolean isNotSuccessful() {
        return HTTP_STATUS_SUCCESSFUL != httpStatus;
    }

    public void add(String key, Object value) {
        model.put(key, value);
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
