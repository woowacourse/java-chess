package chess.domain.piece.position;

import chess.util.SmallLetterAsciiConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//todo: add validation logic
public class Position {
    private static final int MIN = 1;
    private static final int MAX = 8;
    public static final int FORWARD_AMOUNT = 1;

    private final int x;
    private final int y;

    private Position(int x, int y) {
        validateInRange(x, y);
        this.x = x;
        this.y = y;
    }

    static Position of(String x, String y) {
        int convertedX = SmallLetterAsciiConverter.convert(x);
        int convertedY = Integer.parseInt(y);
        return of(convertedX,convertedY);
    }

    public static Position of(String position) {
        return of(position.substring(0,1), position.substring(1,2));
    }

    public static Position of(int x, int y) {
        return new Position(x,y);
    }

    public Distance calculateDistance(Position to) {
        return Distance.calculate(this, to);
    }

    public Distance calculateHorizontalDistance(Position to) {
        return Distance.calculateHorizontal(this, to);
    }

    public boolean isStraightDirection(Position to) {
        return isPerpendicularDirection(to) || isStraightDiagonalDirection(to);
    }

    private boolean isStraightDiagonalDirection(Position to) {
        Distance verticalDistance = Distance.calculateVertical(this, to);
        Distance horizontalDistance = Distance.calculateHorizontal(this, to);
        return isDiagonalDirection(to) && verticalDistance.equals(horizontalDistance);
    }

    public boolean isNotStraightDirection(Position to) {
        return isNotPerpendicularDirection(to) && isNotStraightDiagonalDirection(to);
    }

    private boolean isNotPerpendicularDirection(Position to) {
        Direction direction = Direction.calculate(this, to);
        return direction.isNotPerpendicular();
    }

    public boolean isPerpendicularDirection(Position to) {
        Direction direction = Direction.calculate(this, to);
        return direction.isPerpendicular();
    }

    public boolean isVerticalDirection(Position to) {
        Direction direction = Direction.calculate(this, to);
        return direction.isVertical();
    }


    public boolean isDiagonalDirection(Position to) {
        Direction direction = Direction.calculate(this, to);
        return direction.isDiagonal();
    }

    public boolean isNotStraightDiagonalDirection(Position to) {
        Distance verticalDistance = Distance.calculateVertical(this, to);
        Distance horizontalDistance = Distance.calculateHorizontal(this, to);
        return (isDiagonalDirection(to) && !verticalDistance.equals(horizontalDistance)) || isPerpendicularDirection(to);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.valueOf(x) + String.valueOf(y);
    }
}
