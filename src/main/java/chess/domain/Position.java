package chess.domain;

import chess.domain.exception.IllegalPieceMoveException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {
    private static final Map<File, Map<Rank, Position>> cache = new EnumMap<>(File.class);

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

    public static Position of(char fileName, char rankName) {
        return of(String.valueOf(fileName), String.valueOf(rankName));
    }

    private static Position of(String fileName, String rankName) {
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
        validateStraight(destination);
        if (isDiagonal(destination)) {
            return createDiagonalPath(destination);
        }
        return createCrossPath(destination);
    }

    private void validateStraight(Position destination) {
        int rankDifference = calculateRankDifference(destination);
        int fileDifference = calculateFileDifference(destination);
        if (rankDifference == 0 || fileDifference == 0) {
            return;
        }
        if (Math.abs(rankDifference) == Math.abs(fileDifference)) {
            return;
        }
        throw new IllegalPieceMoveException();
    }

    private boolean isDiagonal(Position destination) {
        int rankDifference = rank.calculateDifference(destination.rank);
        int fileDifference = file.calculateDifference(destination.file);
        return Math.abs(rankDifference) == Math.abs(fileDifference);
    }

    private List<Position> createDiagonalPath(Position destination) {
        List<Rank> ranks = rank.createPath(destination.rank);
        List<File> files = file.createPath(destination.file);
        ArrayList<Position> result = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++) {
            result.add(Position.of(files.get(i), ranks.get(i)));
        }
        return result;
    }

    private List<Position> createCrossPath(Position destination) {
        if (calculateRankDifference(destination) == 0) {
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

    public int calculateRankDifference(Position other) {
        return rank.calculateDifference(other.rank);
    }

    public int calculateFileDifference(Position other) {
        return file.calculateDifference(other.file);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
