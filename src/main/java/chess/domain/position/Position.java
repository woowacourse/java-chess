package chess.domain.position;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Position {

    private static final int VALID_STRAIGHT = 0;
    private static final List<Position> KNIGHT_PATH = Collections.emptyList();

    private final FileCoordinate fileCoordinate;
    private final RankCoordinate rankCoordinate;

    public Position(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        this.fileCoordinate = fileCoordinate;
        this.rankCoordinate = rankCoordinate;
    }

    public int calculateFileGap(Position targetPosition) {
        return fileCoordinate.calculateGap(targetPosition.fileCoordinate);
    }

    public int calculateRankGap(Position targetPosition) {
        return rankCoordinate.calculateGap(targetPosition.rankCoordinate);
    }

    public List<Position> findPath(Position targetPosition) {
        List<RankCoordinate> betweenRanks = rankCoordinate.betweenRanks(targetPosition.rankCoordinate);
        List<FileCoordinate> betweenFiles = fileCoordinate.betweenFiles(targetPosition.fileCoordinate);

        if (calculateFileGap(targetPosition) == VALID_STRAIGHT || calculateRankGap(targetPosition) == VALID_STRAIGHT) {
            return betweenStraight(betweenRanks, betweenFiles);
        }
        if (betweenRanks.size() != betweenFiles.size()) {
            return KNIGHT_PATH;
        }
        return getPathByDiagonal(betweenRanks, betweenFiles);
    }

    private List<Position> betweenStraight(List<RankCoordinate> ranks, List<FileCoordinate> files) {
        if (files.isEmpty()) {
            return betweenRankStraight(ranks);
        }
        return betweenFileStraight(files);
    }

    private List<Position> betweenRankStraight(List<RankCoordinate> ranks) {
        return ranks.stream()
                .map(rank -> new Position(fileCoordinate, rank))
                .collect(toList());
    }

    private List<Position> betweenFileStraight(List<FileCoordinate> files) {
        return files.stream()
                .map(file -> new Position(file, rankCoordinate))
                .collect(toList());
    }

    private List<Position> getPathByDiagonal(List<RankCoordinate> ranks, List<FileCoordinate> files) {
        return IntStream.range(0, ranks.size())
                .mapToObj(index -> new Position(files.get(index), ranks.get(index)))
                .collect(toList());
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
