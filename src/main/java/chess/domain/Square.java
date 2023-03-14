package chess.domain;

import java.util.*;

public class Square {
    private static final Map<File, Map<Rank, Square>> CACHE;

    private final File file;
    private final Rank rank;

    static {
        CACHE = new LinkedHashMap<>();
        for (File file : File.values()) {
            CACHE.put(file, createFile(file));
        }
    }

    private static Map<Rank, Square> createFile(final File file) {
        Map<Rank, Square> ranks = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, new Square(file, rank));
        }
        return ranks;
    }

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
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
}
