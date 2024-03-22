package chess.domain.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import chess.domain.chessboard.attribute.Direction;

public class Square {

    private static final Map<String, Square> SQUARES = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(final String square) {
        return of(File.of(square.charAt(0)), Rank.of(square.charAt(1)));
    }

    public static Square of(final File file, final Rank rank) {
        if (SQUARES.isEmpty()) {
            initializeSquares();
        }
        return SQUARES.get(keyOf(file, rank));
    }

    private static void initializeSquares() {
        for (final Rank rank : Rank.values()) {
            putSquares(rank);
        }
    }

    private static void putSquares(final Rank rank) {
        for (final File file : File.values()) {
            SQUARES.put(keyOf(file, rank), new Square(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public static boolean isInRange(final int column, final int row) {
        return File.isInRange(column) && Rank.isInRange(row);
    }

    public Optional<Square> move(final Direction direction) {
        int row = rank.toRow() + direction.getRow();
        int column = file.toColumn() + direction.getColumn();
        if (isInRange(column, row)) {
            return Optional.of(Square.of(File.of(column), Rank.of(row)));
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Square other
                && rank == other.rank
                && file == other.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
