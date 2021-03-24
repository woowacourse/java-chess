package chess.domain.position;

import chess.domain.Team;
import chess.exception.NegativePositionException;

import java.util.Objects;

public final class Position {
    public static final int RANGE_MIN_PIVOT = 0;
    private final int row;
    private final int col;

    public Position(final int row, final int col) {
        validateRange(row, col);
        this.row = row;
        this.col = col;
    }

    private void validateRange(final int row, final int col) {
        if (row < RANGE_MIN_PIVOT || col < RANGE_MIN_PIVOT) {
            throw new NegativePositionException();
        }
    }

    public final boolean sameCol(final int col) {
        return this.col == col;
    }

    public final boolean isInitPositionByTeam(final Team team) {
        return team.isInitPawn(row);
    }

    public final int getRow() {
        return row;
    }

    public final int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
