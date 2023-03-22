package chess.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {

    private static final Map<File, Map<Rank, Position>> cache = new EnumMap<>(File.class);
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    static {
        for (File file : File.values()) {
            Map<Rank, Position> rankPositionHashMap = new EnumMap<>(Rank.class);
            for (Rank rank : Rank.values()) {
                rankPositionHashMap.put(rank, new Position(file, rank));
            }
            cache.put(file, rankPositionHashMap);
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String position) {
        return of(position.charAt(FILE_INDEX), position.charAt(RANK_INDEX));
    }

    public static Position of(char fileName, char rankName) {
        File file = File.from(fileName);
        Rank rank = Rank.from(rankName);
        Map<Rank, Position> rankToPosition = cache.get(file);
        return rankToPosition.get(rank);
    }

    public static Position of(File file, Rank rank) {
        Map<Rank, Position> rankToPosition = cache.get(file);
        return rankToPosition.get(rank);
    }

    public List<Position> createStraightPath(Position destination) {
        if (isNotStraight(destination)) {
            return Collections.emptyList();
        }
        if (isDiagonal(destination)) {
            return createDiagonalPath(destination);
        }
        return createCrossPath(destination);
    }

    private boolean isNotStraight(Position destination) {
        int rankDifference = getRankDifference(destination);
        int fileDifference = getFileDifference(destination);
        if (rankDifference == 0 || fileDifference == 0) {
            return false;
        }
        return Math.abs(rankDifference) != Math.abs(fileDifference);
    }

    private boolean isDiagonal(Position destination) {
        int rankDifference = rank.getDifference(destination.rank);
        int fileDifference = file.getDifference(destination.file);
        return Math.abs(rankDifference) == Math.abs(fileDifference);
    }

    private List<Position> createDiagonalPath(Position destination) {
        List<Rank> ranks = rank.createPath(destination.rank);
        List<File> files = file.createPath(destination.file);
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++) {
            result.add(Position.of(files.get(i), ranks.get(i)));
        }
        return result;
    }

    private List<Position> createCrossPath(Position destination) {
        if (getRankDifference(destination) == 0) {
            return createFilePath(destination);
        }
        return createRankPath(destination);
    }

    private List<Position> createFilePath(Position destination) {
        List<File> files = file.createPath(destination.file);
        return files.stream()
                .map(it -> Position.of(it, rank))
                .collect(Collectors.toList());
    }

    private List<Position> createRankPath(Position destination) {
        List<Rank> ranks = rank.createPath(destination.rank);
        return ranks.stream()
                .map(it -> Position.of(file, it))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    public int getRankDifference(Position other) {
        return rank.getDifference(other.rank);
    }

    public int getFileDifference(Position other) {
        return file.getDifference(other.file);
    }
}
