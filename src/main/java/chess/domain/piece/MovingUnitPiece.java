package chess.domain.piece;

import java.util.List;

import chess.domain.position.Movement;
import chess.domain.position.UnitDirection;

public abstract class MovingUnitPiece extends Piece {
    List<UnitDirection> movableDirections;

    MovingUnitPiece(Color color, double score, List<UnitDirection> directions) {
        super(color, score);
        this.movableDirections = directions;
    }

    @Override
    public boolean canMove(Movement movement, Piece target) {
        return movement.hasSame(movableDirections);
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    abstract public boolean isPawn();

    @Override
    abstract public boolean isKing();
}
