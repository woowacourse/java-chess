package chess.domain.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.domain.chessboard.attribute.Direction;

public class Square {

    private static final Map<String, Square> SQUARES = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(final File file, final Rank rank) {
        if (SQUARES.isEmpty()) {
            createSquares();
        }
        return SQUARES.get(keyOf(file, rank));
    }

    private static void createSquares() {
        for (final Rank rank : Rank.values()) {
            createSquaresOf(rank);
        }
    }

    private static void createSquaresOf(final Rank rank) {
        for (final File file : File.values()) {
            SQUARES.put(keyOf(file, rank), new Square(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
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

    public Square move(final Direction direction) {
        int row = rank.getValue() + direction.getRow();
        int column = file.getColumn() + direction.getColumn();
        return Square.of(File.of(column), Rank.of(row));
    }
}
