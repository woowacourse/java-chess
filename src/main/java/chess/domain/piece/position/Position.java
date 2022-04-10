package chess.domain.piece.position;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private final File file;
    private final Rank rank;

    private static final Map<String, Position> pool = new HashMap<>();

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return pool.computeIfAbsent(file.name() + rank.name(), ignored -> new Position(file, rank));
    }

    public static Position of(String input) {
        return Position.of(File.of(input.substring(0, 1)), Rank.of(input.substring(1, 2)));
    }

    public boolean isBlocked(Direction direction) {
        Position next = getNext(direction);
        return next.getFile() == File.OUT || next.getRank() == Rank.OUT;
    }

    public Position getNext(Direction direction) {
        return Position.of(file.getNext(direction.getFile()), rank.getNext(direction.getRank()));
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return getFile().name() + getRank().getValue();
    }
}
