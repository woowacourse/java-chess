package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class QueenMovingPattern extends LinearMovingPattern {
    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
        int distanceX = Math.abs(source.calculateDisplacementXTo(target));
        int distanceY = Math.abs(source.calculateDisplacementYTo(target));

        return (isDisplaced(distanceX, distanceY) && (isStraightMove(distanceX, distanceY) || isDiagonalMove(distanceX, distanceY)));
    }

    private boolean isDisplaced(int distanceX, int distanceY) {
        return Math.max(distanceX, distanceY) > 0;
    }

    private boolean isStraightMove(int distanceX, int distanceY) {
        return distanceX * distanceY == 0;
    }

    private boolean isDiagonalMove(int distanceX, int distanceY) {
        return distanceX == distanceY;
    }
}
