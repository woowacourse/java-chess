package chess.domain.square;

import chess.domain.piece.Direction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Square {
    private static final Map<String, Square> CACHE;

    static {
        CACHE = new HashMap<>();
        for (File file : File.values()) {
            createFile(file);
        }
    }

    private static void createFile(final File file) {
        for (Rank rank : Rank.values()) {
            Square square = new Square(file, rank);
            CACHE.put(square.toString(), square);
        }
    }

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(final String square) {
        if (!CACHE.containsKey(square)) {
            throw new IllegalArgumentException("해당 칸은 존재하지 않습니다.");
        }
        return CACHE.get(square);
    }

    public static Square of(final File file, final Rank rank) {
        return CACHE.get(file.toString() + rank.toString());
    }

    public static List<Square> getAllSquares() {
        return new ArrayList<>(CACHE.values());
    }

    public int calculateDistance(final Square target) {
        BigInteger fileDifference = BigInteger.valueOf(calculateFileDifference(target));
        BigInteger rankDifference = BigInteger.valueOf(calculateRankDifference(target));
        return fileDifference.gcd(rankDifference).intValue();
    }

    public int calculateRankDifference(final Square target) {
        return rank.calculateDifference(target.rank);
    }

    public int calculateFileDifference(final Square target) {
        return file.calculateDifference(target.file);
    }

    public Square nextSquare(final Direction direction) {
        int fileUnit = direction.getFileUnit();
        int rankUnit = direction.getRankUnit();
        File nextFile = file.getNextFile(fileUnit);
        Rank nextRank = rank.getNextRank(rankUnit);
        return of(nextFile, nextRank);
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
