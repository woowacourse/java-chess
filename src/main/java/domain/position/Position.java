package domain.position;

import java.util.HashMap;
import java.util.Map;

public record Position(File file, Rank rank) {
    private static final Map<String, Position> CACHE = new HashMap<>();

    public static Position of(File file, Rank rank) {
        String key = generateCacheKey(file, rank);
        return CACHE.computeIfAbsent(key, notUsedKey -> new Position(file, rank));
    }

    private static String generateCacheKey(File file, Rank rank) {
        return String.format("(%d, %d)", file.getIndex(), rank.getIndex());
    }

    public int rowIndex() {
        return rank.getIndex();
    }

    public int columnIndex() {
        return file.getIndex();
    }

    public Position add(final UnitVector unitVector) {
        File newFile = File.of(columnIndex() + unitVector.getCol());
        Rank newRank = Rank.of(rowIndex() + unitVector.getRow());

        return Position.of(newFile, newRank);
    }
}
