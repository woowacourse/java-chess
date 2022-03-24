package chess.domain.position;

import chess.domain.position.direction.Direction;
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
        final Rank rank = Rank.of(toRankValue(position.substring(1)));
        return new Position(file, rank);
    }

    private static int toRankValue(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 Rank 값 입니다.");
        }
    }

    public Position move(final Direction direction) {
        return direction.move(file, rank);
    }

    public boolean isSameRank(final Rank rank) {
        return rank == this.rank;
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
