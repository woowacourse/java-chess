package model.position;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static model.position.Direction.dColumn;
import static model.position.Direction.dRow;

public class Moving {

    private final Position currentPosition;
    private final Position nextPosition;

    public Moving(Position currentPosition, Position nextPosition) {
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }

    public boolean isHorizontal() {
        final int currentRow = currentPosition.getRowIndex();
        final int nextRow = nextPosition.getRowIndex();

        return currentRow == nextRow;
    }

    public boolean isVertical() {
        final int currentColumn = currentPosition.getColumnIndex();
        final int nextColumn = nextPosition.getColumnIndex();

        return currentColumn == nextColumn;
    }

    public boolean isDiagonal() {
        final int currentRow = currentPosition.getRowIndex();
        final int currentColumn = currentPosition.getColumnIndex();

        final int nextRow = nextPosition.getRowIndex();
        final int nextColumn = nextPosition.getColumnIndex();

        return Math.abs(currentRow - nextRow) == Math.abs(currentColumn - nextColumn);
    }

    public boolean isNotMoved() {
        return Objects.equals(currentPosition, nextPosition);
    }

    public Set<Position> route() {
        final int currentRow = currentPosition.getRowIndex();
        final int currentColumn = currentPosition.getColumnIndex();
        final int index = Direction.from(currentPosition, nextPosition).getIndex();

        Set<Position> result = new HashSet<>();
        for (int i = 1; i < distance(); i++) {
            Row row = Row.from(currentRow + (i * dRow[index]));
            Column column = Column.from(currentColumn + (i * dColumn[index]));
            result.add(new Position(column, row));
        }
        return result;
    }

    private int distance() {
        final int currentRow = currentPosition.getRowIndex();
        final int currentColumn = currentPosition.getColumnIndex();

        final int nextRow = nextPosition.getRowIndex();
        final int nextColumn = nextPosition.getColumnIndex();

        return Math.max(Math.abs(currentRow - nextRow), Math.abs(currentColumn - nextColumn));
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    @Override
    public boolean equals(Object target) {
        if (target == this) {
            return true;
        }
        if (!(target instanceof Moving moving)) {
            return false;
        }
        return Objects.equals(currentPosition, moving.currentPosition) && Objects.equals(nextPosition,
                moving.nextPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition, nextPosition);
    }

    @Override
    public String toString() {
        return "Moving[" +
                "currentPosition=" + currentPosition + ", " +
                "nextPosition=" + nextPosition + ']';
    }
}
