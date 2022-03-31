package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Queen extends LinearMovePiece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "q";
    }

    @Override
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

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return findLinearRoute(source, target);
    }

    @Override
    public double score() {
        return 9;
    }
}
