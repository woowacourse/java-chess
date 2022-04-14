package chess.model.PieceArrangement;

import java.util.HashMap;
import java.util.Map;

import chess.MappingUtil;
import chess.model.Position;
import chess.model.piece.Piece;
import chess.model.piece.PieceCache;

public class SavedPieceArrangement implements PieceArrangement {
    private final Map<String, String> value;

    public SavedPieceArrangement(Map<String, String> stringPiecesByPositions) {
        this.value = stringPiecesByPositions;
    }

    @Override
    public Map<Position, Piece> apply() {
        Map<Position, Piece> result = new HashMap<>();
        for (Map.Entry<String, String> entry : value.entrySet()) {
            if (entry.getValue().equals("empty")) {
                continue;
            }
            result.put(Position.of(entry.getKey()), PieceCache.of(MappingUtil.emblemFrom(entry.getValue())));
        }
        return result;
    }
}
