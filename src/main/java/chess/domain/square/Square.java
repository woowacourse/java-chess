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

    public static Square of(Square square, int fileIncrementBy, int rankIncrementBy) {
        Objects.requireNonNull(square, "square은 필수입니다");
        File currentFile = square.getFile();
        Rank currentRank = square.getRank();
        File fileIncremented = File.of(currentFile.getNumber() + fileIncrementBy);
        Rank rankIncremented = Rank.of(currentRank.getNumber() + rankIncrementBy);
        return Square.of(fileIncremented, rankIncremented);
    }

    private static void validateFileRank(File file, Rank rank) {
        Objects.requireNonNull(file, "file은 필수입니다");
        Objects.requireNonNull(rank, "rank는 필수입니다");
        String name = file.getName() + rank.getName();
        if (!CACHE.containsKey(name)) {
            throw new IllegalArgumentException("잘못된 square의 입력입니다");
        }
    }

    private final File file;
    private final Rank rank;
    private final String name;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
        this.name = file.getName() + rank.getName();
    }

    public Square movedSquareInBoundary(int fileIncrementBy, int rankIncrementBy) {
        if (isIncrementedInBoundary(fileIncrementBy, rankIncrementBy)) {
            return Square.of(this, fileIncrementBy, rankIncrementBy);
        }
        return this;
    }

    public Square movedSquareInBoundary(Direction direction) {
        File currentFile = this.getFile();
        Rank currentRank = this.getRank();
        if (isIncrementedInBoundary(direction.getFileIncrement(), direction.getRankIncrement())) {
            File fileIncremented = File.of(currentFile.getNumber() + direction.getFileIncrement());
            Rank rankIncremented = Rank.of(currentRank.getNumber() + direction.getRankIncrement());
            return Square.of(fileIncremented, rankIncremented);
        }
        return this;
    }

    public boolean isIncrementedInBoundary(int fileIncrementBy, int rankIncrementBy) {
        return file.validateIncrementNumber(fileIncrementBy) && rank.validateIncrementNumber(rankIncrementBy);
    }

    public boolean isJustSameFile(Square square) {
        return (this != square) && (this.file == square.file);
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
}
