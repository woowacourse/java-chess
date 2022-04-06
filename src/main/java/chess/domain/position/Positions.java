package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

public class Positions {

    private static final Map<String, Position> CACHE = new HashMap<>();

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
        return CACHE.get(rankFile);
    }
}
