package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private final static Map<String, Position> positions = new HashMap<>();

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return positions.computeIfAbsent(toKey(file, rank),
                position -> new Position(file, rank));
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public int calculateRankGap(Position other) {
        return rank.subtractOrder(other.rank);
    }

    public int calculateFileGap(Position other) {
        return file.subtractOrder(other.file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public Position move(final int fileStep, final int rankStep) {
        File newFile = file.move(fileStep);
        Rank newRank = rank.move(rankStep);
        return Position.of(newFile, newRank);
    }

    public String getPosition() {
        return file.name() + rank.name();
    }
}
