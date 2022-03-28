package chess.domain.piece.position;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private static final Map<String, Position> POOL = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return POOL.computeIfAbsent(file.name() + rank.name(), ignored -> new Position(file, rank));
    }

    public boolean isBlocked(Direction direction) {
        Position next = findNext(direction);
        return next.getFile() == File.Out || next.getRank() == Rank.Out;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public Position findNext(Direction direction) {
        return Position.of(file.findNext(direction.file()), rank.findNext(direction.rank()));
    }
}
