package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Row;

public final class InitialPiecePositionChecker {

    private final Column column;
    private final Row row;

    InitialPiecePositionChecker(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    boolean isKing() {
        return column == Column.E && isNotPawnOrNone();
    }

    boolean isQueen() {
        return column == Column.D && isNotPawnOrNone();
    }

    boolean isRook() {
        return (column == Column.A || column == Column.H) && isNotPawnOrNone();
    }

    boolean isBishop() {
        return (column == Column.C || column == Column.F) && isNotPawnOrNone();
    }

    boolean isKnight() {
        return (column == Column.B || column == Column.G) && isNotPawnOrNone();
    }

    private boolean isNotPawnOrNone() {
        return row == Row.ONE || row == Row.EIGHT;
    }

    boolean isPawn() {
        return row == Row.TWO || row == Row.SEVEN;
    }

    boolean isNone() {
        return row == Row.THREE || row == Row.FOUR || row == Row.FIVE || row == Row.SIX;
    }
}
