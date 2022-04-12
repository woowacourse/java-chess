package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

public class Positions {

    private static final Map<String, Position> CACHE = new HashMap<>();
    private static final String NOT_EXIST_POSITION = "존재하지 않는 위치입니다.";

    static {
        for (Rank rank : Rank.values()) {
            storeRankFile(rank);
        }
    }

    private static void storeRankFile(Rank rank) {
        for (File file : File.values()) {
            CACHE.put(file.file() + rank.rank(), Position.of(rank.rank(), file.file()));
        }
    }

    private Positions() {
    }

    public static Position findPosition(String rankFile) {
        if (CACHE.get(rankFile) == null) {
            throw new RuntimeException(NOT_EXIST_POSITION);
        }
        return CACHE.get(rankFile);
    }
}
