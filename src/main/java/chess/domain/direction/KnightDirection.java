package chess.domain.direction;

import chess.domain.position.Position;

import java.util.Objects;

public enum KnightDirection {
    NORTH_EAST(1, 2),
    NORTH_WEST(-1, 2),
    EAST_NORTH(2, 1),
    EAST_SOUTH(2, -1),
    SOUTH_EAST(1, -2),
    SOUTH_WEST(-1, -2),
    WEST_SOUTH(-2, -1),
    WEST_NORTH(-2, 1);

    private int rowDiff;
    private int columnDiff;

    KnightDirection(int rowDiff, int columnDiff) {
        this.rowDiff = rowDiff;
        this.columnDiff = columnDiff;
    }

    public boolean contains(Position source, Position target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);

        return source.getRow().ordinal() + rowDiff == target.getRow().ordinal()
                && source.getColumn().ordinal() + columnDiff == target.getColumn().ordinal();
    }
}