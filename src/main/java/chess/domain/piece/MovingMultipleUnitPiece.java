package chess.domain.piece;

import java.util.List;

import chess.domain.position.Direction;

public abstract class MovingMultipleUnitPiece extends Piece {
    List<Direction> movableDirections;

    MovingMultipleUnitPiece(Color color, double score, List<Direction> directions) {
        super(color, score);
        this.movableDirections = directions;
    }

    @Override
    public boolean canMove(Direction direction, Piece target) {
        checkSameTeam(target);
        return direction.hasMultiple(movableDirections);
    }
}
