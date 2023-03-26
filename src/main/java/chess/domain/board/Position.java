package chess.domain.board;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {

    private static final int START_PATH_RANGE = 1;
    private static final int ZERO_DISTANCE = 0;

    private final FileCoordinate fileCoordinate;
    private final RankCoordinate rankCoordinate;

    public Position(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        this.fileCoordinate = fileCoordinate;
        this.rankCoordinate = rankCoordinate;
    }

    public List<Position> findStraightPaths(Position targetPosition) {
        if (isNotStraight(targetPosition)) {
            return Collections.emptyList();
        }

        int columnStep = fileCoordinate.compare(targetPosition.fileCoordinate);
        int rowStep = rankCoordinate.compare(targetPosition.rankCoordinate);
        int distance = getDistance(targetPosition);
        return IntStream.range(START_PATH_RANGE, distance)
                .mapToObj(coordinate -> createPosition(columnStep, rowStep, coordinate))
                .collect(Collectors.toList());
    }

    private Position createPosition(int columnStep, int rowStep, int coordinate) {
        return new Position(FileCoordinate.findBy(this.getColumn() + (columnStep * coordinate)),
                RankCoordinate.findBy(this.getRow() + (rowStep * coordinate)));
    }

    private int getDistance(Position targetPosition) {
        int columnDistance = calculateColumnDistance(targetPosition);
        int rowDistance = calculateRowDistance(targetPosition);
        return Math.max(columnDistance, rowDistance);
    }

    private boolean isNotStraight(Position targetPosition) {
        int columnDistance = calculateColumnDistance(targetPosition);
        int rowDistance = calculateRowDistance(targetPosition);
        return columnDistance != ZERO_DISTANCE && rowDistance != ZERO_DISTANCE && columnDistance != rowDistance;
    }

    public int calculateColumnDistance(Position targetPosition) {
        return fileCoordinate.calculateDistance(targetPosition.fileCoordinate);
    }

    public int calculateRowDistance(Position targetPosition) {
        return rankCoordinate.calculateDistance(targetPosition.rankCoordinate);
    }

    public boolean isDiagonal(Position targetPosition) {
        int columnDistance = calculateColumnDistance(targetPosition);
        int rowDistance = calculateRowDistance(targetPosition);
        return columnDistance == rowDistance;
    }

    public boolean isStraight(Position targetPosition) {
        return (fileCoordinate.compare(targetPosition.fileCoordinate) == 0
                || rankCoordinate.compare(targetPosition.rankCoordinate) == 0);
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
