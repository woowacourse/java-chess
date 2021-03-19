package chess.domain.dto;

import chess.domain.board.Direction;

import java.util.List;

public class Strategy {
    private final List<Direction> directions;
    private final int moveRange;

    public Strategy(List<Direction> directions, int moveRange) {
        this.directions = directions;
        this.moveRange = moveRange;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public boolean containsDirection(Direction currentDirection) {
        return directions.contains(currentDirection);
    }
}
