package chess.domain.piece.strategy;

import chess.domain.piece.Direction;

import java.util.List;

public enum PawnDirection {

    UPPER(List.of(Direction.UP, Direction.UPLEFT, Direction.UPRIGHT)),
    LOWER(List.of(Direction.DOWN, Direction.DOWNLEFT, Direction.DOWNRIGHT));

    private final List<Direction> movableDirections;

    PawnDirection(final List<Direction> movableDirections) {
        this.movableDirections = movableDirections;
    }

    public List<Direction> getMovableDirections() {
        return movableDirections;
    }
}
