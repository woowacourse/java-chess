package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    private static final Map<String, Position> CACHE;

    static {
        CACHE = new HashMap<>();
        for (Rank rank : Rank.values()) {
            cacheEachRank(rank);
        }
    }

    private static void cacheEachRank(final Rank rank) {
        for (File file : File.values()) {
            CACHE.put(file.toString() + rank.toString(), new Position(file, rank));
        }
    }

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return CACHE.get(file.toString() + rank.toString());
    }

    public static Position from(String positionCommand) {
        return CACHE.get(positionCommand);
    }


    public int calculateRankDistance(final Position start) {
        return rank.calculateDistance(start.rank);
    }

    public int calculateFileDistance(final Position start) {
        return file.calculateDistance(start.file);
    }

    public Position move(final int fileDirection, final int rankDirection) {
        return Position.of(file.plus(fileDirection), rank.plus(rankDirection));
    }
    public boolean isSameFile(File file){
        return this.file==file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
