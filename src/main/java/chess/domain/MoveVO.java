package chess.domain;

import java.util.List;

public class MoveVO {
    private List<Direction> directions;
    private int moveRange;

    public MoveVO(List<Direction> directions, int moveRange) {
        this.directions = directions;
        this.moveRange = moveRange;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public void setMoveRange(int moveRange) {
        this.moveRange = moveRange;
    }

    public boolean containsDirection(Direction currentDirection) {
        return directions.contains(currentDirection);
    }
}
