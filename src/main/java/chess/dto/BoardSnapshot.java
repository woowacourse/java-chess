package chess.dto;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class BoardSnapshot {

    private final Map<Position, Piece> snapShot;

    public BoardSnapshot(Map<Position, Piece> snapShot) {
        this.snapShot = snapShot;
    }

    public Piece findByPosition(Position position) {
        if (!snapShot.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 적절하지 않은 위치입니다.");
        }
        return snapShot.get(position);
    }
}
