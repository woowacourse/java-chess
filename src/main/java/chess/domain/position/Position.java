package chess.domain.position;

import chess.domain.Team;

import java.util.Objects;

import static chess.domain.board.Board.RANGE_MAX_PIVOT;
import static chess.domain.board.Board.RANGE_MIN_PIVOT;

public final class Position {
    private final int row;
    private final int col;

    public Position(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public final boolean sameCol(final int col) {
        return this.col == col;
    }

    public final boolean isInitPawnPositionByTeam(final Team team) {
        if (Team.WHITE.equals(team)) {
            return row == Row.getLocation("2");
        }
        return row == Row.getLocation("7");
    }

    public Position next(int rowDir, int colDir) {
        return new Position(this.row + rowDir, this.col + colDir);
    }

    public boolean outOfRange() {
        return (row < RANGE_MIN_PIVOT || row > RANGE_MAX_PIVOT || col < RANGE_MIN_PIVOT || col > RANGE_MAX_PIVOT);
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
