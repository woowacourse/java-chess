package chess.domain.position;

import java.util.Objects;

public class Position {

    private static final int RANK_CHARACTER_INDEX = 0;
    private static final int FILE_CHARACTER_INDEX = 1;

    private static final int POSITION_WORD_LENGTH = 2;
    private static final char START_FILE_CHARACTER = 'a';
    private static final char START_RANK_CHARACTER = '1';

    private static final String ERROR_MISMATCH_WORD_LENGTH = "위치는 a1 과 같은 형식의 2글자이어야 합니다.";

    private final Point x;
    private final Point y;

    public Position(String value) {
        this(from(value));
    }

    private Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    private Position(Point x, Point y) {
        this.x = x;
        this.y = y;
    }

    public static Position from(String value) {
        if (value.length() != POSITION_WORD_LENGTH) {
            throw new IllegalArgumentException(ERROR_MISMATCH_WORD_LENGTH);
        }

        int x = value.charAt(RANK_CHARACTER_INDEX) - START_FILE_CHARACTER;
        int y = value.charAt(FILE_CHARACTER_INDEX) - START_RANK_CHARACTER;
        return Position.of(x, y);
    }

    public static Position of(int x, int y) {
        final Point pointX = Point.from(x);
        final Point pointY = Point.from(y);
        return new Position(pointX, pointY);
    }

    public int getX() {
        return x.getPoint();
    }

    public int getY() {
        return y.getPoint();
    }

    public Position add(int xDistance, int yDistance) {
        return new Position(this.x.add(xDistance), this.y.add(yDistance));
    }

    public boolean isInRange() {
        return x.isInRange() && y.isInRange();
    }

    public boolean existsAtRankOf(int point) {
        return y.equalsTo(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
