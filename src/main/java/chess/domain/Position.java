package chess.domain;

import java.util.EnumMap;
import java.util.Map;

public class Position {
    private static final Map<File, Map<Rank, Position>> cache = new EnumMap<>(File.class);

    static {
        for (File file : File.values()) {
            Map<Rank, Position> rankPositionHashMap = new EnumMap<>(Rank.class);
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

    public static Position of(File file, Rank rank) {
        Map<Rank, Position> rankToPosition = cache.get(file);
        return rankToPosition.get(rank);
    }
}
