package chess.controller.dto;

import chess.domain.Position;
import chess.domain.turn.GameTurn;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameResponse {

    private final String turn;
    private final Map<String, PieceResponse> pieceResponses;

    public ChessGameResponse(String turn, Map<String, PieceResponse> pieceResponses) {
        this.turn = turn;
        this.pieceResponses = pieceResponses;
    }

    public static ChessGameResponse from(GameTurn gameTurn) {
        return new ChessGameResponse(gameTurn.currentTurn(), createPieceResponses(gameTurn));
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
        result.put("turn", turn);
        result.putAll(pieceResponses);
        return result;
    }
}
