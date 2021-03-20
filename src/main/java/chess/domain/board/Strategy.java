package chess.domain.board;

import chess.domain.board.Direction;

import java.util.List;

public class Strategy {
    private final List<Direction> directions;
    private int moveRange;

    public Strategy(List<Direction> directions, int moveRange) {
        this.directions = directions;
        this.moveRange = moveRange;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public void moveTowards(Direction currentDirection) {
        if (!directions.contains(currentDirection)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표로 이동할 수 없습니다.");
        }
    }

    public boolean canMove() {
        moveRange -= 1;
        return moveRange >= 0;
    }
}
