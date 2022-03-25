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

    public boolean isBlocked(Direction direction) {
        Position next = getNext(direction);
        if (isUpDownLeftRight(direction)) {
            return this == next;
        }

        return (getFile() == next.getFile()) || (getRank() == next.getRank());
    }

    private boolean isUpDownLeftRight(Direction direction) {
        return direction == Direction.Up || direction == Direction.Down
            || direction == Direction.Left || direction == Direction.Right;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public Position getNext(Direction direction) {
        return Position.of(file.getNext(direction.file()), rank.getNext(direction.rank()));
    }
}
