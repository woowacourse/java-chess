package chess.domain.board;

import chess.domain.piece.Direction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Square {
    private static final Map<String, Square> CACHE;

    static {
        CACHE = new LinkedHashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                String squareName = convertToFormat(file, rank);
                Square square = new Square(file, rank);
                CACHE.put(squareName, square);
            }
        }
    }

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(final String fileAndRank) {
        return CACHE.get(fileAndRank.toLowerCase());
    }

    public static Square of(final File file, final Rank rank) {
        return CACHE.get(convertToFormat(file, rank));
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

    private static String convertToFormat(final File file, final Rank rank) {
        String squareFormat = String.format("%s%d", file, rank.getPosition());
        return squareFormat.toLowerCase();
    }

    public Square next(final Direction direction) {
        int fileUnit = direction.getFileUnit();
        int rankUnit = direction.getRankUnit();
        File nextFile = file.getNextFile(fileUnit);
        Rank nextRank = rank.getNextRank(rankUnit);
        return of(nextFile, nextRank);
    }

    @Override
    public String toString() {
        String squareFormat = String.format("%s%d", file, rank.getPosition());
        return squareFormat.toLowerCase();
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
