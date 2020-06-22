package chess.domain.position;

import chess.domain.piece.state.PiecesState;
import chess.domain.piece.team.Team;
import chess.util.AsciiConverter;

import java.util.Objects;

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

    private static Position of(String x, String y) {
        int convertedX = AsciiConverter.convert(x);
        int convertedY = Integer.parseInt(y);
        return of(convertedX, convertedY);
    }

    public static Position of(String position) {
        return of(position.substring(0, 1), position.substring(1, 2));
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public Distance calculateStraightDistance(Position to) {
        return Distance.calculateStraight(this, to);
    }

    public boolean isStraightDirection(Position to) {
        return isPerpendicularDirection(to) || isStraightDiagonalDirection(to);
    }

    public boolean isNotStraightDirection(Position to) {
        return isNotPerpendicularDirection(to) && isNotStraightDiagonalDirection(to);
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

    public boolean ofIntializedPawn(Team team) {
        if (team.isWhite()) {
            return y == PiecesState.WHITE_PAWN_ROW;
        }
        return y == PiecesState.BLACK_PAWN_ROW;
    }

    public String serialize() {
        char pattern = (char) (x + AsciiConverter.SMALL_LETTER_CONVERTING_NUMBER);
        return String.valueOf(pattern) + String.valueOf(y);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    private boolean isPerpendicularDirection(Position to) {
        Direction direction = Direction.calculate(this, to);
        return direction.isPerpendicular();
    }

    public boolean isNotPerpendicularDirection(Position to) {
        Direction direction = Direction.calculate(this, to);
        return direction.isNotPerpendicular();
    }

    private static void validateInRange(int x, int y) {
        if (x < MIN || MAX < x) {
            throw new IllegalArgumentException("x의 범위를 벗어납니다.");
        }
        if (y < MIN || MAX < y) {
            throw new IllegalArgumentException("y의 범위를 벗어납니다.");
        }
    }

    private boolean isStraightDiagonalDirection(Position to) {
        Distance verticalDistance = Distance.calculateVertical(this, to);
        Distance horizontalDistance = Distance.calculateHorizontal(this, to);
        return isDiagonalDirection(to) && verticalDistance.equals(horizontalDistance);
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
