package chess.domain;

import java.util.Objects;

public final class Position {

    private final char file;
    private final int rank;

    private Position(final char file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final char file, final int rank) {
        return new Position(file, rank);
    }

    public static Position from(final String value) {
        final char file = value.charAt(0);
        final int rank = value.charAt(1) - '0';
        return new Position(file, rank);
    }

    public char file() {
        return file;
    }

    public int rank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
