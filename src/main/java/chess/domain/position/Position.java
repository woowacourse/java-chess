package chess.domain.position;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class Position {

    private static final Map<String, Position> CACHE;

    static {
        final Map<String, Position> positions = new HashMap<>();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                positions.put(getCacheKey(file, rank), new Position(file, rank));
            }
        }

        CACHE = positions;
    }

    private static String getCacheKey(final File file, final Rank rank) {
        return file.symbol() + rank.index();
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return CACHE.get(getCacheKey(file, rank));
    }

    public static Position from(final String position) {
        validateString(position);
        return CACHE.get(position);
    }

    private static void validateString(final String position) {
        if (!CACHE.containsKey(position)) {
            throw new IllegalArgumentException("잘못된 위치입니다.");
        }
    }

    public @Nullable Position move(final MovePattern movePattern) {
        final int nextFileIndex = movePattern.nextFileIndex(fileIndex());
        final int nextRankIndex = movePattern.nextRankIndex(rankIndex());

        if (!Board.isInRange(nextFileIndex, nextRankIndex)) {
            return null;
        }

        return Position.of(File.findByIndex(nextFileIndex), Rank.findByIndex(nextRankIndex));
    }

    public int fileIndex() {
        return file.index();
    }

    public int rankIndex() {
        return rank.index();
    }
}
