package chess.domain.position;

import static java.util.stream.Collectors.toList;

import chess.view.FileCoordinateView;
import chess.view.RankCoordinateView;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {

    private static final List<Position> KNIGHT_PATH = Collections.emptyList();
    private static final int VALID_STRAIGHT = 0;
    private static final int POSITION_FILE = 0;
    private static final int POSITION_RANK = 1;

    private final FileCoordinate fileCoordinate;
    private final RankCoordinate rankCoordinate;

    public Position(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        this.fileCoordinate = fileCoordinate;
        this.rankCoordinate = rankCoordinate;
    }

    public static Position of(String position) {
        List<String> fileAndRank = Arrays.stream(position.split(""))
                .collect(Collectors.toList());
        return new Position(FileCoordinateView.findBy(fileAndRank.get(POSITION_FILE)),
                RankCoordinateView.findBy(fileAndRank.get(POSITION_RANK)));
    }

    public int calculateFileGap(Position targetPosition) {
        return fileCoordinate.calculateGap(targetPosition.fileCoordinate);
    }

    public int calculateRankGap(Position targetPosition) {
        return rankCoordinate.calculateGap(targetPosition.rankCoordinate);
    }

    public List<Position> findPathWithoutSourceAndTarget(Position targetPosition) {
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

    public FileCoordinate getFileCoordinate() {
        return fileCoordinate;
    }

    public RankCoordinate getRankCoordinate() {
        return rankCoordinate;
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

    public boolean isSameRank(RankCoordinate other) {
        return rankCoordinate == other;
    }
}
