package chess.domain.square;

import chess.domain.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Square {
    private final static Map<String, Square> CACHE = new HashMap<>();
    public static int MIN_NUM = 1;
    public static int MAX_NUM = 8;

    private final File file;
    private final Rank rank;
    private final String name;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
        this.name = file.getName() + rank.getName();
    }

    static {
        IntStream.rangeClosed(1, 8)
                .forEach(fileIndex -> IntStream.rangeClosed(1, 8).forEach(rankIndex -> putCACHE(fileIndex, rankIndex)));
    }

    private static void putCACHE(int fileIndex, int rankIndex) {
        File file = File.of(fileIndex);
        Rank rank = Rank.of(rankIndex);
        CACHE.putIfAbsent(file.getName() + rank.getName(), new Square(file, rank));
    }

    public static Square of(String squareName) {
        if (!CACHE.containsKey(squareName)) {
            throw new IllegalArgumentException("유효한 square의 값이 아닙니다");
        }
        return CACHE.get(squareName);
    }

    public static Square of(File file, Rank rank) {
        validateFileRank(file, rank);
        return Square.of(file.getName() + rank.getName());
    }

    private static void validateFileRank(File file, Rank rank) {
        Objects.requireNonNull(file, "file은 필수입니다");
        Objects.requireNonNull(rank, "rank는 필수입니다");
        String name = file.getName() + rank.getName();
        if (!CACHE.containsKey(name)) {
            throw new IllegalArgumentException("잘못된 square의 입력입니다");
        }
    }

    public Square movedSquareInBoundary(int fileIncrementBy, int rankIncrementBy) {
        if (isIncrementedInBoundary(fileIncrementBy, rankIncrementBy)) {
            File currentFile = getFile();
            Rank currentRank = getRank();
            File fileIncremented = File.of(currentFile.getNumber() + fileIncrementBy);
            Rank rankIncremented = Rank.of(currentRank.getNumber() + rankIncrementBy);
            return Square.of(fileIncremented, rankIncremented);
        }
        return this;
    }

    public Square movedSquareInBoundary(Direction direction, int count) {
        File currentFile = this.getFile();
        Rank currentRank = this.getRank();
        if (isIncrementedInBoundary(direction.getFileIncrement() * count, direction.getRankIncrement() * count)) {
            File fileIncremented = File.of(currentFile.getNumber() + direction.getFileIncrement() * count);
            Rank rankIncremented = Rank.of(currentRank.getNumber() + direction.getRankIncrement() * count);
            return Square.of(fileIncremented, rankIncremented);
        }
        return this;
    }

    public boolean isIncrementedInBoundary(int fileIncrementBy, int rankIncrementBy) {
        return file.validateIncrementNumber(fileIncrementBy) && rank.validateIncrementNumber(rankIncrementBy);
    }

    public boolean isDiagonalDirectionDistanceOne(Square nextSquare) {
        File currentFile = this.getFile();
        Rank currentRank = this.getRank();

        File nextFile = nextSquare.getFile();
        Rank nextRank = nextSquare.getRank();

        int fileDifference = Math.abs(currentFile.getNumber() - nextFile.getNumber());
        int rankDifference = Math.abs(currentRank.getNumber() - nextRank.getNumber());

        return fileDifference == 1 && rankDifference == 1;
    }

    public boolean isJustSameFile(Square nextSquare) {
        return (this != nextSquare) && (this.file == nextSquare.file);
    }

    public int calculateDistanceRank(Square nextSquare) {
        Rank currentRank = this.getRank();
        Rank nextRank = nextSquare.getRank();

        return Math.abs(currentRank.getNumber() - nextRank.getNumber());
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
