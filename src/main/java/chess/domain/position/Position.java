package chess.domain.position;

import chess.domain.position.movestrategy.MoveStrategy;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position create(final String position) {
        final File file = File.of(position.charAt(0));
        final Rank rank = Rank.of(position.substring(1));
        return new Position(file, rank);
    }

    public Position move(final MoveStrategy moveStrategy) {
        return moveStrategy.move(file, rank);
    }

    public boolean isSameRank(final Rank rank) {
        return rank == this.rank;
    }

    public boolean isSameFile(final File file) {
        return file == this.file;
    }

    public int calculateFileDistance(final Position from) {
        return this.file.calculateDistance(from.file);
    }

    public int calculateRankDistance(final Position from) {
        return this.rank.calculateDistance(from.rank);
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
