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
    // TODO static import를 사용한 이유?
    private static final Map<String, Position> POOL = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .mapToObj(RowPosition::new)
            .flatMap(rowPosition -> IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                    .mapToObj(ColumnPosition::new)
                    .map(columnPosition -> new Position(rowPosition, columnPosition)))
            .collect(toMap(position -> Position.toKey(position.rowPosition, position.columnPosition), position -> position));

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

    public boolean rowIs(RowPosition rowPosition) {
        return this.rowPosition.equals(rowPosition);
    }

    public List<Position> findPath(Position target) {
        try {
            Direction direction = DirectionJudge.judge(this, target);
            List<Position> path = new ArrayList<>();
            Position nextPosition = movePositionDirectionTo(direction);

            while (!target.equals(nextPosition)) {
                path.add(nextPosition);
                nextPosition = nextPosition.movePositionDirectionTo(direction);
            }
            return path;
        } catch (IllegalArgumentException e) {
            return List.of();
        }
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

    // TODO 움직이는 행위가 구현되어 있음
    private Position movePositionDirectionTo(Direction direction) {
        int nextRowStep = direction.getRowWeight();
        int nextColumnStep = direction.getColumnWeight();
        return movePosition(nextRowStep, nextColumnStep);
    }

    private Position movePosition(int rowInterval, int columnInterval) {
        return Position.of(rowPosition.findRowIntervalWith(rowInterval)
                , columnPosition.findColumnIntervalWith(columnInterval));
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
