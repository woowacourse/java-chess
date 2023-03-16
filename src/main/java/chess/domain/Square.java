package chess.domain;

import chess.domain.piece.Direction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Square {
    private static final Map<File, Map<Rank, Square>> CACHE;

    static {
        CACHE = new LinkedHashMap<>();
        for (File file : File.values()) {
            CACHE.put(file, createFile(file));
        }
    }

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static Map<Rank, Square> createFile(final File file) {
        Map<Rank, Square> ranks = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, new Square(file, rank));
        }
        return ranks;
    }

    public static Square of(final File file, final Rank rank) {
        Map<Rank, Square> rankSquareMap = CACHE.get(file);
        return rankSquareMap.get(rank);
    }

    public static List<Square> getAllSquares() {
        List<Square> squares = new ArrayList<>();
        CACHE.values().forEach(file -> squares.addAll(file.values()));
        return squares;
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

    public Square next(final Direction direction) {
        int fileUnit = direction.getFileUnit();
        int rankUnit = direction.getRankUnit();
        File nextFile = file.getNextFile(fileUnit);
        Rank nextRank = rank.getNextRank(rankUnit);
        return of(nextFile, nextRank);
    }
}
