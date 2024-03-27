package domain.position;

import java.util.HashMap;
import java.util.Map;
import view.mapper.FileInput;
import view.mapper.RankInput;

public class PositionGenerator {

    private static final Map<File, Map<Rank, Position>> CACHE = new HashMap<>();

    static {
        for (File file : File.values()) {
            Map<Rank, Position> rankPosition = rankPosition(file);
            CACHE.put(file, rankPosition);
        }
    }

    private static Map<Rank, Position> rankPosition(File file) {
        Map<Rank, Position> rankPosition = new HashMap<>();
        for (Rank rank : Rank.values()) {
            Position position = new Position(file, rank);
            rankPosition.put(rank, position);
        }
        return rankPosition;
    }

    public static Position generate(File file, Rank rank) {
        return CACHE.getOrDefault(file, new HashMap<>()).getOrDefault(rank, new Position(file, rank));
    }

    public static Position generate(String rawFile, String rawRank) {
        File file = FileInput.asFile(rawFile);
        Rank rank = RankInput.asRank(rawRank);
        return generate(file, rank);
    }
}
