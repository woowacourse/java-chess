package chess.domain.piece;

import java.util.List;

import chess.domain.position.Direction;

public abstract class MovingUnitPiece extends Piece{
    List<Direction> movableDirections;

    MovingUnitPiece(Color color, double score, List<Direction> directions) {
        super(color, score);
        this.movableDirections = directions;
    }

    @Override
    public boolean canMove(Direction direction, Piece target) {
        checkSameTeam(target);
        return direction.hasSame(movableDirections);
    }
}
