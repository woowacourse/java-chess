package chess.domain.board;

import chess.domain.piece.DirectionVector;
import java.util.Objects;

public class Square {

    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public int calculateDistanceX(final Square square) {
        return this.file.calculateDistance(square.file);
    }

    public int calculateDistanceY(final Square square) {
        return this.rank.calculateDistance(square.rank);
    }

    public Square next(final DirectionVector direction) {
        return new Square(direction.next(file), direction.next(rank));
    }

    public boolean isInitPawnPosition(final boolean isBlack) {
        if (isBlack) {
            return rank == Rank.SEVEN;
        }
        return rank == Rank.TWO;
    }
}
