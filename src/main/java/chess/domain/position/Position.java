package chess.domain.position;

import static chess.domain.position.ColumnPosition.MAX_NUMBER;
import static chess.domain.position.ColumnPosition.MIN_NUMBER;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Position {
    private static final Map<String, Position> POOL = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .mapToObj(RowPosition::new)
            .flatMap(rowPosition -> IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                    .mapToObj(ColumnPosition::new)
                    .map(columnPosition -> new Position(rowPosition, columnPosition)))
            .collect(toMap(position -> Position.toKey(position.rowPosition, position.columnPosition),
                    position -> position));

    private final RowPosition rowPosition;
    private final ColumnPosition columnPosition;

    public Position(RowPosition rowPosition, ColumnPosition columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    public static Position of(int rowPosition, int colPosition) {
        RowPosition row = new RowPosition(rowPosition);
        ColumnPosition col = new ColumnPosition(colPosition);
        return POOL.get(toKey(row, col));
    }

    private static String toKey(RowPosition rowPosition, ColumnPosition colPosition) {
        return String.valueOf(rowPosition) + String.valueOf(colPosition);
    }

    public Position verticalReversePosition() {
        return POOL.get(toKey(rowPosition.reverse(), this.columnPosition));
    }

    public boolean isStraightWith(Position target) {
        return rowPosition.equals(target.rowPosition) || columnPosition.equals(target.columnPosition);
    }

    public boolean isDiagonalWith(Position target) {
        int rowInterval = rowPosition.intervalWith(target.rowPosition);
        int colInterval = columnPosition.intervalWith(target.columnPosition);
        return rowInterval == colInterval;
    }

    public Direction directionTo(Position target) {
        boolean destinationIsAbove = target.rowPosition.isLowerThan(rowPosition);
        return Direction.from(destinationIsAbove);
    }

    public int squaredDistanceWith(Position target) {
        int rowInterval = rowPosition.intervalWith(target.rowPosition);
        int colInterval = columnPosition.intervalWith(target.columnPosition);
        return (int) Math.pow(rowInterval, 2) + (int) Math.pow(colInterval, 2);
    }

    public boolean rowIs(RowPosition rowPosition) {
        return this.rowPosition.equals(rowPosition);
    }

    public List<Position> diagonalPath(Position target) {

        //도착지와 현재 위치의 방향을 찾는다.
        if (!isDiagonalWith(target)) {
            throw new IllegalArgumentException("대각선 경로를 계산할 수 없습니다");
        }

        List<Position> path = new ArrayList<>();
        int nextRowStep = 1;
        int nextColumnStep = 1;

        if (this.rowPosition.isHigherThan(target.rowPosition)) {
            nextRowStep = -1;
        }

        if (this.columnPosition.isRight(target.columnPosition)) {
            nextColumnStep = -1;
        }
        Position nextPosition = this.movePosition(nextRowStep, nextColumnStep);
        while (true) {

            System.out.println("nextRowPosition = " + nextPosition.getRowPosition().getRowNumber());
            System.out.println("nextColPosition = " + nextPosition.getColumnPosition().getColumnNumber());
            if (nextPosition.equals(target)) {
                break;
            }
            path.add(nextPosition);
            nextPosition = nextPosition.movePosition(nextRowStep, nextColumnStep);
        }
        return path;
    }

    public List<Position> straightPath(Position target) {

        //도착지와 현재 위치의 방향을 찾는다.
        if (!isStraightWith(target)) {
            throw new IllegalArgumentException("직선 경로를 계산할 수 없습니다");
        }

        List<Position> path = new ArrayList<>();
        int nextRowStep = 0;
        int nextColumnStep = 0;

        if (this.rowPosition.isLowerThan(target.rowPosition)) {
            nextRowStep = 1;
        }

        if (this.rowPosition.isHigherThan(target.rowPosition)) {
            nextRowStep = -1;
        }

        if (this.columnPosition.isLeft(target.columnPosition)) {
            nextColumnStep = 1;
        }

        if (this.columnPosition.isRight(target.columnPosition)) {
            nextColumnStep = -1;
        }

        Position nextPosition = movePosition(nextRowStep, nextColumnStep);
        while (true) {
            if (nextPosition.equals(target)) {
                break;
            }
            path.add(nextPosition);
            nextPosition = nextPosition.movePosition(nextRowStep, nextColumnStep);
        }
        return path;
    }

    private Position movePosition(int rowMove, int columnMove) {
        return Position.of(rowPosition.move(rowMove), columnPosition.move(columnMove));
    }

    public RowPosition getRowPosition() {
        return rowPosition;
    }

    public ColumnPosition getColumnPosition() {
        return columnPosition;
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
        return Objects.equals(rowPosition, position.rowPosition) && Objects.equals(columnPosition,
                position.columnPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowPosition, columnPosition);
    }
}
