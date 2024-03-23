package chess.domain.square;

import java.util.ArrayList;
import java.util.List;

public class Square {
    private static final String ERROR_INVALID_PATTERN = "문자 1개 숫자 1개를 붙인 위치형식으로 입력해 주세요.";
    private static final String PATTERN = "^[a-z][0-9]$";
    private static final char FILE_LEFT_BOUND = 'a';
    private static final char RANK_UPPER_BOUND = '8';

    private final Rank rank;
    private final File file;

    public Square(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square from(String square) {
        validatePattern(square);
        Rank rank = Rank.from(parseRank(square));
        File file = File.from(parseFile(square));
        return new Square(rank, file);
    }

    private static void validatePattern(String square) {
        if (!square.matches(PATTERN)) {
            throw new IllegalArgumentException(ERROR_INVALID_PATTERN);
        }
    }

    private static int parseRank(String square) {
        return square.charAt(0) - FILE_LEFT_BOUND;
    }

    private static int parseFile(String square) {
        return RANK_UPPER_BOUND - square.charAt(1);
    }

    public List<Square> generatePath(Square target) {
        List<Square> path = new ArrayList<>();
        int vectorRank = rank.vectorTo(target.rank);
        int vectorFile = file.vectorTo(target.file);

        Square current = this;
        do {
            current = current.add(vectorRank, vectorFile);
            path.add(current);
        } while (!current.equals(target));

        return path;
    }

    private Square add(int vectorRank, int vectorFile) {
        return new Square(rank.add(vectorRank), file.add(vectorFile));
    }

    public boolean isSameRank(Square other) {
        return rank == other.rank;
    }

    public boolean isSameRank(Rank otherRank) {
        return rank == otherRank;
    }

    public boolean isSameFile(Square other) {
        return file == other.file;
    }

    public boolean isSameFile(File otherFile) {
        return file == otherFile;
    }

    public boolean isSameDiagonal(Square other) {
        return distanceRankFrom(other) == distanceFileFrom(other);
    }

    public int distanceRankFrom(Square other) {
        return rank.distance(other.rank);
    }

    public int distanceFileFrom(Square other) {
        return file.distance(other.file);
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
        return rank == other.rank && file == other.file;
    }

    public int getFile() {
        return rank.ordinal();
    }

    public int getRank() {
        return file.ordinal();
    }
}
