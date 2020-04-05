package chess.position;

import chess.exception.InvalidPositionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                CACHE.put(getKey(file, rank), new Position(file, rank));
            }
        }
    }

    private final File file;

    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        if (file.isNone() && rank.isNone()) {
            return null;
        }
        return CACHE.get(getKey(file, rank));
    }

    public static Position of(String key) {
        String lowerCaseKey = key.toLowerCase();
        validate(lowerCaseKey);
        return CACHE.get(lowerCaseKey);
    }

    private static String getKey(File file, Rank rank) {
        return file.getName() + rank.getName();
    }

    public String getKey() {
        return this.file.getName() + this.rank.getName();
    }

    private static void validate(String key) {
        if (CACHE.get(key) == null) {
            throw new InvalidPositionException(key);
        }
    }

    public static List<Position> findDiagonalTrace(Position start, Position end) {
        List<Position> positions = new ArrayList<>();
        List<File> files = File.valuesBetween(start.getFile(), end.getFile());
        List<Rank> ranks = Rank.valuesBetween(start.getRank(), end.getRank());
        for (int i = 0; i < files.size(); i++) {
            positions.add(Position.of(files.get(i), ranks.get(i)));
        }
        return positions;
    }

    public int differenceOfRank(Position other) {
        return Math.abs(getRankNumber() - other.getRankNumber());
    }

    public boolean isStraight(Position other) {
        if (this.file == other.file && this.rank == other.rank) {
            return false;
        }
        return this.file == other.file || this.rank == other.rank;
    }

    public boolean isNotStraight(Position other) {
        return !isStraight(other);
    }

    public boolean isDiagonal(Position other) {
        return isSameSum(other) || isSameDifference(other);
    }

    public boolean isSameSum(Position other) {
        int sumOfStartPositionCoordinate = this.getFileNumber() + this.getRankNumber();
        int sumOfEndPositionCoordinate = other.getFileNumber() + other.getRankNumber();
        return sumOfStartPositionCoordinate == sumOfEndPositionCoordinate;
    }

    public boolean isSameDifference(Position other) {
        int differenceOfStartPositionCoordinate = this.getFileNumber() - this.getRankNumber();
        int differenceOfEndPositionCoordinate = other.getFileNumber() - other.getRankNumber();
        return differenceOfStartPositionCoordinate == differenceOfEndPositionCoordinate;
    }

    public boolean isNotDiagonal(Position other) {
        return !isDiagonal(other);
    }

    public boolean isSameRank(Position other) {
        return this.rank == other.rank;
    }

    public boolean isSameFile(Position other) {
        return this.file == other.file;
    }

    public boolean isNotSameFile(Position other) {
        return !isSameFile(other);
    }

    public boolean isNotDistanceOneSquare(Position other) {
        return !isDistanceOneSquare(other);
    }

    private boolean isDistanceOneSquare(Position other) {
        return this.rank.isNear(other.rank) && this.file.isNear(other.file);
    }

    public int multiplicationOfDifferenceBetweenFileAndRank(Position other) {
        return this.file.findDifference(other.file) * this.rank.findDifference(other.rank);
    }

    public int increaseAmountOfRank(Position other) {
        return other.getRankNumber() - getRankNumber();
    }

    public int increaseAmountOfFile(Position other) {
        return other.getFileNumber() - getFileNumber();
    }

    public Position at(Direction direction) {
        File file = this.file.addFile(direction.getIncreaseAmountOfFile());
        Rank rank = this.rank.addRank(direction.getIncreaseAmountOfRank());
        return Position.of(file, rank);
    }

    public File getFile() {
        return this.file;
    }

    public Rank getRank() {
        return this.rank;
    }

    public int getFileNumber() {
        return this.file.getNumber();
    }

    public int getRankNumber() {
        return this.rank.getNumber();
    }
}
