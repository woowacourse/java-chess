package domain;

import java.util.HashMap;
import java.util.Map;

public class Square {

    private static final Map<String, Square> caches = new HashMap<>();

    private final ChessColumn chessColumn;
    private final Rank rank;

    private Square(ChessColumn chessColumn, Rank rank) {
        this.chessColumn = chessColumn;
        this.rank = rank;
    }

    public static Square of(ChessColumn chessColumn, Rank rank) {
        String key = chessColumn.name() + rank.name();
        return caches.computeIfAbsent(key, ignored -> new Square(chessColumn, rank));
    }
}
