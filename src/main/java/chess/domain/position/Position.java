package chess.domain.position;

import chess.domain.Piece.Distance;

import java.util.Arrays;
import java.util.List;

//todo: add validation logic
public class Position {
    private static final int MIN = 1;
    private static final int MAX = 8;
    private static final int FORWARD_AMOUNT = 1;

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Position of(int x, int y) {
        validateInRange(x, y);

        return new Position(x,y);
    }

    public Distance calculateDistance(Position to) {
        return Distance.calculate(this, to);
    }

    public boolean isDiagonalDirection(Position to) {
        return Direction.isDiagonal(this, to);

    }

    public Direction calculateDirection(Position to) {
        return Direction.calculate(this, to);
    }

    public Position go(Direction direction) {
        int newX = x + (FORWARD_AMOUNT * direction.getHorizontal());
        int newY = y + (FORWARD_AMOUNT * direction.getVertical());
        return Position.of(newX, newY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private static void validateInRange(int x, int y) {
        if (x < MIN || MAX < x) {
            throw new IllegalArgumentException("x의 범위를 벗어납니다.");
        }
        if (y < MIN || MAX < y) {
            throw new IllegalArgumentException("y의 범위를 벗어납니다.");
        }
    }

    public List<Position> getForwardDiagonals(Direction teamForwardDirection) {
        Direction eastForward = Direction.EAST.compose(teamForwardDirection);
        Direction westForward = Direction.WEST.compose(teamForwardDirection);
        Position eastDiagonal = this.go(eastForward);
        Position westDiagonal = this.go(westForward);
        return Arrays.asList(eastDiagonal, westDiagonal);
    }

    public boolean isNotForward(Position from, Direction teamForwardDirection) {
        int standard = (y - from.y) * teamForwardDirection.getVertical();
        return standard <= 0;
    }
}
