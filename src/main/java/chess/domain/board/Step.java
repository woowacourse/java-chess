package chess.domain.board;

public class Step {
    private final Direction direction;
    private final SquareState squareState;

    public Step(Direction direction, SquareState squareState) {
        this.direction = direction;
        this.squareState = squareState;
    }

    public boolean isDiagonal() {
        return direction.isDiagonal();
    }

    public boolean isOrthogonal() {
        return direction.isOrthogonal();
    }

    public boolean isEmpty() {
        return squareState.isEmpty();
    }

    public boolean isEnemy() {
        return squareState.isEnemy();
    }

    public boolean isUpside() {
        return direction.isUpside();
    }

    public boolean isDownside() {
        return direction.isDownside();
    }

    public Direction getDirection() {
        return direction;
    }
}
