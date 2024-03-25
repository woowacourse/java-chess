package chess.domain;

import chess.domain.piece.Direction;

import java.util.LinkedHashMap;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    private final File file;
    private final Rank rank;

    static {
        for (File file : File.values()) {
            cacheAllRankWithFile(file);
        }
    }

    private static void cacheAllRankWithFile(File file) {
        for (Rank rank : Rank.values()) {
            CACHE.put(toKey(file, rank), new Position(file, rank));
        }
    }

    private static String toKey(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        Position position = CACHE.get(toKey(file, rank));
        if (position == null) {
            throw new IllegalArgumentException("범위를 벗어난 위치입니다.");
        }

        return position;
    }

    public Position moveTowardDirection(final Direction direction) {
        File file = direction.moveFile(this.file);
        Rank rank = direction.moveRank(this.rank);
        return Position.of(file, rank);
    }

    public Direction calculateDirection(final Position target) {
        File targetFile = target.file;
        Rank targetRank = target.rank;
        int dx = targetFile.calculateDifference(this.file);
        int dy = targetRank.calculateDifference(this.rank);

        return Direction.find(dx, dy);
    }
}
