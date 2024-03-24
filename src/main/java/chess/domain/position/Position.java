package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static chess.domain.position.ColumnPosition.MAX_NUMBER;
import static chess.domain.position.ColumnPosition.MIN_NUMBER;
import static java.util.stream.Collectors.toMap;

public class Position {
    //TODO: 풀 생성 과정 단순화 가능한지 고민해보기
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
        return String.valueOf(rowPosition) + colPosition;
    }

    public boolean isStraightWith(Position target) {
        return rowPosition.equals(target.rowPosition) || columnPosition.equals(target.columnPosition);
    }

    public boolean isDiagonalWith(Position target) {
        int rowInterval = rowPosition.intervalWith(target.rowPosition);
        int colInterval = columnPosition.intervalWith(target.columnPosition);
        return rowInterval == colInterval;
    }

    public int squaredDistanceWith(Position target) {
        int rowInterval = rowPosition.intervalWith(target.rowPosition);
        int colInterval = columnPosition.intervalWith(target.columnPosition);
        return (int) Math.pow(rowInterval, 2) + (int) Math.pow(colInterval, 2);
    }

    // TODO 메서드 라인 수 개선
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

    public boolean isUpPosition(Position target) {
        return rowPosition.isLowerThan(target.rowPosition);
    }

    public boolean isDownPosition(Position target) {
        return rowPosition.isHigherThan(target.rowPosition);
    }

    public boolean isLeftPosition(Position target) {
        return columnPosition.isLeft(target.columnPosition);
    }

    public boolean isRightPosition(Position target) {
        return columnPosition.isRight(target.columnPosition);
    }

    private Position movePosition(int rowMove, int columnMove) {
        return Position.of(rowPosition.move(rowMove), columnPosition.move(columnMove));
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
