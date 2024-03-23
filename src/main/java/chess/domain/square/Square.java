package chess.domain.square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Square {
    private static final String ERROR_INVALID_PATTERN = "문자 1개 숫자 1개를 붙인 위치형식으로 입력해 주세요.";
    private static final String PATTERN = "^[a-z][0-9]$";
    private static final char FILE_LEFT_BOUND = 'a';
    private static final char RANK_UPPER_BOUND = '8';
    private static final Map<String, Square> cache = new HashMap<>();

    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(String square) {
        validatePattern(square);
        return cache.computeIfAbsent(square, s -> {
                File file = File.from(parseFile(square));
                Rank rank = Rank.from(parseRank(square));
                return new Square(file, rank);
        });
    }

    public static Square of(File file, Rank rank) {
        String squareKey = generateSquareKey(file, rank);
        return cache.computeIfAbsent(squareKey, k -> new Square(file, rank));
    }

    private static void validatePattern(String square) {
        if (!square.matches(PATTERN)) {
            throw new IllegalArgumentException(ERROR_INVALID_PATTERN);
        }
    }

    private static int parseFile(String square) {
        return RANK_UPPER_BOUND - square.charAt(1);
    }

    private static int parseRank(String square) {
        return square.charAt(0) - FILE_LEFT_BOUND;
    }

    private static String generateSquareKey(File file, Rank rank) {
        return file.name().toLowerCase() + rank.ordinal();
    }

    public List<Square> generatePath(Square target) {
        List<Square> path = new ArrayList<>();
        int vectorFile = file.vectorTo(target.file);
        int vectorRank = rank.vectorTo(target.rank);

        Square current = this;
        do {
            current = current.add(vectorFile, vectorRank);
            path.add(current);
        } while (!current.equals(target));

        return path;
    }

    private Square add(int vectorFile, int vectorRank) {
        return new Square(file.add(vectorFile), rank.add(vectorRank));
    }

    public boolean isSameFile(Square other) {
        return file == other.file;
    }

    public boolean isSameFile(File otherFile) {
        return file == otherFile;
    }

    public boolean isSameRank(Square other) {
        return rank == other.rank;
    }

    public boolean isSameRank(Rank otherRank) {
        return rank == otherRank;
    }

    public boolean isSameDiagonal(Square other) {
        return distanceRankFrom(other) == distanceFileFrom(other);
    }

    public int distanceFileFrom(Square other) {
        return file.distance(other.file);
    }

    public int distanceRankFrom(Square other) {
        return rank.distance(other.rank);
    }

    public boolean isUpperThan(Square other) {
        return rank.compareTo(other.rank) < 0;
    }

    public boolean isLowerThan(Square other) {
        return rank.compareTo(other.rank) > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Square other)) return false;
        return file == other.file && rank == other.rank;
    }

    public int getFileOrdinal() {
        return rank.ordinal();
    }

    public int getRankOrdinal() {
        return file.ordinal();
    }
}
