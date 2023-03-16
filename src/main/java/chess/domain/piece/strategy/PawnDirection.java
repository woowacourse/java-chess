package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.square.Direction;

import java.util.List;

public enum PawnDirection {

    UPPER(List.of(Direction.UP, Direction.UPLEFT, Direction.UPRIGHT)),
    LOWER(List.of(Direction.DOWN, Direction.DOWNLEFT, Direction.DOWNRIGHT));

    private final List<Direction> movableDirections;

    PawnDirection(final List<Direction> movableDirections) {
        this.movableDirections = movableDirections;
    }

    public static PawnDirection from(final Color color) {
        if (color.equals(Color.WHITE)) {
            return UPPER;
        }
        return LOWER;
    }

    public boolean isExist(int fileDifference, int rankDifference) {
        return movableDirections.stream()
                .anyMatch(direction ->
                        direction.getFileDifference() == fileDifference && direction.getRankDifference() == rankDifference);
    }

    public List<Direction> getMovableDirections() {
        return movableDirections;
    }
}
