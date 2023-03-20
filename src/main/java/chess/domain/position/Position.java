package chess.domain.position;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final Map<Integer, Position> CACHE;

    static {
        final Map<Integer, Position> positions = new HashMap<>();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                positions.put(getKey(rank, file), new Position(file, rank));
            }
        }

        CACHE = positions;
    }

    private static int getKey(final Rank rank, final File file) {
        return (rank.index() - Board.LOWER_BOUNDARY) * Board.UPPER_BOUNDARY + file.index();
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return CACHE.get(getKey(rank, file));
    }

    public Position move(final MovePattern movePattern) {
        final int nextFileIndex = movePattern.nextFileIndex(fileIndex());
        final int nextRankIndex = movePattern.nextRankIndex(rankIndex());

        if (Board.isInRange(nextFileIndex, nextRankIndex)) {
            return Position.of(File.findByIndex(nextFileIndex), Rank.findByIndex(nextRankIndex));
        }

        return this;
    }

    public boolean isDiagonal(final Position other) {
        final int deltaFile = this.fileIndex() - other.fileIndex();
        final int deltaRank = this.rankIndex() - other.rankIndex();

        return Math.abs(deltaFile) == Math.abs(deltaRank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public int fileIndex() {
        return file.index();
    }

    public int rankIndex() {
        return rank.index();
    }
}
