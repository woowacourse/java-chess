package chess.domain.position;

import chess.domain.Team;

import java.util.Objects;

public final class Position {
    private static final int RANGE_MIN_PIVOT = 0;
    private static final int START_POINT_INDEX = 0;
    private static final int END_POINT_INDEX = 1;

    private final int row;
    private final int column;

    public Position(final String point) {
        this(
                Character.getNumericValue(point.charAt(START_POINT_INDEX)),
                Character.getNumericValue(point.charAt(END_POINT_INDEX))
        );
    }

    public Position(final int row, final int column) {
        validateRange(row, column);
        this.row = row;
        this.column = column;
    }

    private void validateRange(final int row, final int column) {
        if (row < RANGE_MIN_PIVOT || column < RANGE_MIN_PIVOT) {
            throw new IllegalArgumentException("좌표에는 음수가 들어갈 수 없습니다.");
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean sameColumn(final int column) {
        return this.column == column;
    }

    public boolean isInitPositionByTeam(final Team team) {
        return team.isInitPawn(row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
