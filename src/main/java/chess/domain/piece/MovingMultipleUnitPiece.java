package chess.domain.piece;

import java.util.List;

import chess.domain.position.Direction;
import chess.domain.position.UnitDirection;

public abstract class MovingMultipleUnitPiece extends Piece {
    List<UnitDirection> movableDirections;

    MovingMultipleUnitPiece(Color color, double score, List<UnitDirection> directions) {
        super(color, score);
        this.movableDirections = directions;
    }

    @Override
    public boolean canMove(Direction direction, Piece target) {
        return direction.hasMultiple(movableDirections);
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
