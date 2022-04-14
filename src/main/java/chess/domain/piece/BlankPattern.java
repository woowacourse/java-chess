package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class BlankPattern implements MovingPattern {
    @Override
    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    public List<Position> findRoute(Position source, Position target) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }
}
