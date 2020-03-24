package chess.position;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                CACHE.put(file.getName() + rank.getName(), new Position(file, rank));
            }
        }
    }

    public Position(File file, Rank rank) {

    }


    public static Position of(File file, Rank rank) {

    }
}
