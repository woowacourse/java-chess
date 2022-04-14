package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KnightMovingPattern implements MovingPattern {
    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
        int distanceX = Math.abs(source.calculateDisplacementXTo(target));
        int distanceY = Math.abs(source.calculateDisplacementYTo(target));

        return isKnightMove(distanceX, distanceY);
    }

    private boolean isKnightMove(int distanceX, int distanceY) {
        return (distanceY == 1 && distanceX == 2) || (distanceY == 2 && distanceX == 1);
    }

    public List<Position> findRoute(Position source, Position target) {
        return List.of();
    }
}
