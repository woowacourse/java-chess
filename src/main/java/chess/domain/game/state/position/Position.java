package chess.domain.game.state.position;

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
        return POOL.computeIfAbsent(file.name() + rank.getValue(), ignored -> new Position(file, rank));
    }

    public static Position of(String file, String rank) {
        return POOL.computeIfAbsent(file + rank, ignored -> new Position(File.of(file), Rank.of(Integer.parseInt(rank))));
    }

    public boolean isBlocked(Direction direction) {
        Position next = findNext(direction);
        return next.file == File.Out || next.rank == Rank.Out;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public Position findNext(Direction direction) {
        return Position.of(file.findNext(direction.getFile()), rank.findNext(direction.getRank()));
    }
}
