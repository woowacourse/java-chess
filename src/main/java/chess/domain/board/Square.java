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

    public static Square from(final String value) {
        validate(value);
        final File file = File.from(value.charAt(0));
        final Rank rank = Rank.from(value.charAt(1));
        return new Square(file, rank);
    }

    private static void validate(final String value) {
        if (value.length() != 2) {
            throw new IllegalArgumentException("체스 칸으로 변환할 수 없습니다.");
        }
    }

    public int calculateDistanceFile(final Square square) {
        return this.file.calculateDistance(square.file);
    }

    public int calculateDistanceRank(final Square square) {
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

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
