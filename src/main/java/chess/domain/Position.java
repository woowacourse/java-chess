package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private static final Map<File, Map<Rank, Position>> cache = new HashMap<>();

    static {
        for (File file : File.values()) {
            HashMap<Rank, Position> rankPositionHashMap = new HashMap<>();
            for (Rank rank : Rank.values()) {
                rankPositionHashMap.put(rank, new Position(file, rank));
            }
            cache.put(file, rankPositionHashMap);
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String fileName, String rankName) {
        File file = File.from(fileName);
        Rank rank = Rank.from(rankName);
        Map<Rank, Position> rankToPosition = cache.get(file);
        return rankToPosition.get(rank);
    }
}
