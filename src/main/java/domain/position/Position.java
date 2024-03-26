package domain.position;

import domain.movement.ChessVector;
import domain.movement.Direction;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final String fileRank) {
        this(new File(fileRank.charAt(0)), new Rank(fileRank.charAt(1)));
    }

    public Position move(final Direction direction) {
        int fileVector = direction.getFileVector();
        int rankVector = direction.getRankVector();
        return new Position(file.add(fileVector), rank.add(rankVector));
    }

    public ChessVector toVector(Position target) {
        return new ChessVector(file.subtract(target.file), rank.subtract(target.rank));
    }

    public boolean isAtRank2() {
        return rank.isRank2();
    }

    public boolean isAtRank7() {
        return rank.isRank7();
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
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
