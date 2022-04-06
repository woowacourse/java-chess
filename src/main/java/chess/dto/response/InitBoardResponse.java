package chess.dto.response;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitBoardResponse {

    private List<Map<String, String>> board;

    public InitBoardResponse(List<Map<String, String>> board) {
        this.board = board;
    }

    public static InitBoardResponse from(Map<Position, Piece> board) {

        final List<Map<String, String>> boardResponse = new ArrayList<>();

        for (Position position : board.keySet()) {
            final Piece piece = board.get(position);
            final Map<String, String> keyValue = getPiecePositionResponse(position, piece);

            boardResponse.add(keyValue);
        }

        return new InitBoardResponse(boardResponse);
    }

    private static Map<String, String> getPiecePositionResponse(Position position, Piece piece) {
        final Map<String, String> keyValue = new HashMap<>();

        keyValue.put("position", PositionResponse.from(position));
        keyValue.put("piece", piece.getClass().getSimpleName());
        keyValue.put("team", piece.getPieceTeam().name().toLowerCase());

        return keyValue;
    }
}
