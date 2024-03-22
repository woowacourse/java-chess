package chess.domain.board;

public class Step {
    private final Direction direction;
    private final SquareState squareState;

    public Step(Direction direction, SquareState squareState) {
        this.direction = direction;
        this.squareState = squareState;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isDiagonal() {
        return direction.isDiagonal();
    }

    public boolean isOrthogonal() {
        return direction.isOrthogonal();
    }

    public boolean hasPiece() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return squareState.isEmpty();
    }

    public boolean isEnemy() {
        return squareState.isEnemy();
    }

    public boolean isUpside() {
        return direction.isUpSide();
    }

    public boolean isDownside() {
        return direction.isDownside();
    }
}
