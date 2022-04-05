package chess.domain.position;

import chess.domain.move.Direction;
import java.util.Objects;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position create(final String position) {
        final File file = File.from(position.substring(FILE_INDEX, RANK_INDEX));
        final Rank rank = Rank.from(position.substring(RANK_INDEX));
        return new Position(file, rank);
    }

    public Position move(final Direction direction) {
        return new Position(direction.moveFile(file), direction.moveRank(rank));
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

    public String getPosition() {
        return file.getValue() + rank.getValue();
    }
}
