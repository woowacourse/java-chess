package chess.controller.dto;

import chess.domain.Position;
import chess.domain.turn.GameTurn;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameResponse {

    private final boolean isEnd;
    private final Map<String, PieceResponse> pieceResponses;

    public ChessGameResponse(boolean isEnd, Map<String, PieceResponse> pieceResponses) {
        this.isEnd = isEnd;
        this.pieceResponses = pieceResponses;
    }

    public static ChessGameResponse from(GameTurn gameTurn) {
        return new ChessGameResponse(gameTurn.isEnd(), createPieceResponses(gameTurn));
    }

    private static Map<String, PieceResponse> createPieceResponses(final GameTurn gameTurn) {
        return gameTurn.pieces()
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(entry -> positionName(entry.getKey()), entry -> PieceResponse.from(entry.getValue())));
    }

    private static String positionName(Position position) {
        return String.format("%c%c", position.column(), position.row());
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("isEnd", isEnd);
        result.putAll(pieceResponses);
        return result;
    }
}
