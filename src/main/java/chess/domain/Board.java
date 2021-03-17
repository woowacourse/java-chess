package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> positionMap;

    public Board(Map<Position, Piece> positionMap) {
        this.positionMap = positionMap;
    }

    public boolean containsPosition(Position position) {
        return positionMap.containsKey(position);
    }

    public Piece pieceAt(Position position) {
        return positionMap.get(position);
    }
}
