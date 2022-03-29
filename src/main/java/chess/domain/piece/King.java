package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;

public class King extends LinearMovePiece {
    public King(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "k";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDisplacementXTo(target));
        int distanceY = Math.abs(source.calculateDisplacementYTo(target));

        return isDisplaced(distanceX, distanceY) && isOnlySingleMovePerDirection(distanceX, distanceY);
    }

    private boolean isDisplaced(int distanceX, int distanceY) {
        return distanceX + distanceY > 0;
    }

    private boolean isOnlySingleMovePerDirection(int distanceX, int distanceY) {
        return Math.max(distanceX, distanceY) == 1;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return findLinearRoute(source, target);
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
