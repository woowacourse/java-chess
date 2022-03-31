package chess.domain.piece;

import java.util.List;

import chess.domain.position.Direction;
import chess.domain.position.UnitDirection;

public abstract class MovingUnitPiece extends Piece {
    List<UnitDirection> movableDirections;

    MovingUnitPiece(Color color, double score, List<UnitDirection> directions) {
        super(color, score);
        this.movableDirections = directions;
    }

    @Override
    public boolean canMove(Direction direction, Piece target) {
        checkSameTeam(target);
        return direction.hasSame(movableDirections);
    }
}
