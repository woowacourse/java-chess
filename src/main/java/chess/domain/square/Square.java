package chess.domain.square;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Square {

    private static final Map<String, Square> cache = new HashMap<>(64);
    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        // TODO: 나중에 key 방식 변경해야함
        final String cardKey = String.valueOf(List.of(file.getValue(), rank.getValue()));
        if (cache.containsKey(cardKey)) {
            return cache.get(cardKey);
        }
        Square square = new Square(file, rank);
        cache.put(cardKey, square);
        return square;
    }
}
