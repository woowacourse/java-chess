package chess.domain.board;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {

    private final FileCoordinate fileCoordinate;
    private final RankCoordinate rankCoordinate;

    public Position(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        this.fileCoordinate = fileCoordinate;
        this.rankCoordinate = rankCoordinate;
    }

    public List<Position> findPath(Position targetPosition) {
        int columnStep = getStep(this.getColumn(), targetPosition.getColumn());
        int rowStep = getStep(this.getRow(), targetPosition.getRow());
        int difference = getDifference(targetPosition);

        if (isNotStraight(targetPosition)) {
            return Collections.emptyList();
        }
        return IntStream.range(1, difference)
                .mapToObj(coordinate -> createPosition(columnStep, rowStep, coordinate))
                .collect(Collectors.toList());
    }

    private Position createPosition(int columnStep, int rowStep, int coordinate) {
        return new Position(FileCoordinate.findBy(this.getColumn() + (columnStep * coordinate)),
                RankCoordinate.findBy(this.getRow() + (rowStep * coordinate)));
    }

    private int getDifference(Position targetPosition) {
        int columnDifference = Math.abs(targetPosition.getColumn() - this.getColumn());
        int rowDifference = Math.abs(targetPosition.getRow() - this.getRow());
        return Math.max(columnDifference, rowDifference);
    }

    private boolean isNotStraight(Position targetPosition) {
        int columnDifference = Math.abs(targetPosition.getColumn() - this.getColumn());
        int rowDifference = Math.abs(targetPosition.getRow() - this.getRow());
        return columnDifference != 0 && rowDifference != 0 && columnDifference != rowDifference;
    }

    private int getStep(int nowCoordinate, int targetCoordinate) {
        return Integer.compare(targetCoordinate, nowCoordinate);
    }

    public FileCoordinate getFileCoordinate() {
        return fileCoordinate;
    }

    public RankCoordinate getRankCoordinate() {
        return rankCoordinate;
    }

    public int getColumn() {
        return fileCoordinate.getColumnNumber();
    }

    public int getRow() {
        return rankCoordinate.getRowNumber();
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
        return fileCoordinate == position.fileCoordinate && rankCoordinate == position.rankCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileCoordinate, rankCoordinate);
    }
}
