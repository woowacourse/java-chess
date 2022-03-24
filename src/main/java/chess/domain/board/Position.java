package chess.domain.board;

import java.util.Objects;

public class Position {

    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int VALID_SIZE = 2;

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(String input) {
        validateBlank(input);
        validateSize(input);

        String[] values = input.split("");
        return new Position(Column.of(values[COLUMN_INDEX]), Row.of(values[ROW_INDEX]));
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("포지션은 빈값일 수 없습니다.");
        }
    }

    private static void validateSize(String input) {
        if (input.length() != VALID_SIZE) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    public Position move(int x, int y) {
        return new Position(column.move(x), row.move(y));
    }

    // TODO 테스트없음
    public boolean isStartRow(Row rank) {
        return row == rank;
    }

    public boolean canCrossMovingStraight(Direction direction, Position dest) {
        return moveToNextPositionCheckingDestination(this, dest, direction.getX(), direction.getY());
    }

    private boolean moveToNextPositionCheckingDestination(Position start, Position dest, int x, int y) {
        if (!start.canMove(x, y)) {
            return false;
        }
        Position nextPosition = start.move(x, y);
        if (nextPosition.equals(dest)) {
            return true;
        }
        return moveToNextPositionCheckingDestination(nextPosition, dest, x, y);
    }

    private boolean canMove(int x, int y) {
        return column.canMove(x) && row.canMove(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                column +
                ", " + row +
                '}';
    }
}
