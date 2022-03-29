package chess.game;

import java.util.List;

public enum Direction {

    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1),

    NNE(1, 2),
    ENE(2, 1),
    ESE(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    WSW(-2, -1),
    WNW(-2, 1),
    NNW(-1, 2);

    private static final int WHITE_PAWN_BEGINNING_DISTANCE = 2;
    private static final int BLACK_PAWN_BEGINNING_DISTANCE = -2;
    private static final int NOT_MOVE = 0;

    private final int column;
    private final int row;

    Direction(final int column, final int row) {
        this.column = column;
        this.row = row;
    }

    public static List<Direction> getWhitePawnDirections() {
        return List.of(N, NW, NE);
    }

    public static List<Direction> getBlackPawnDirections() {
        return List.of(S, SW, SE);
    }

    public static List<Direction> getKnightDirections() {
        return List.of(NNE, ENE, ESE, SSE, SSW, WSW, WNW, NNW);
    }

    public boolean canDiagonalMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return Math.abs(columnDistance) == Math.abs(rowDistance);
    }

    public boolean canHorizontalAndVerticalMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return columnDistance == NOT_MOVE || rowDistance == NOT_MOVE;
    }

    public boolean canAnyDirectionMove(final Position from, final Position to) {
        return canHorizontalAndVerticalMove(from, to) || canDiagonalMove(from, to);
    }

    public boolean canKingMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return isEqualTo(columnDistance, rowDistance);
    }

    public boolean canWhitePawnMove(final int columnDistance, final int rowDistance, final boolean pawnAtInitial) {
        if (rowDistance == WHITE_PAWN_BEGINNING_DISTANCE && columnDistance == NOT_MOVE && this == N && pawnAtInitial) {
            return isEqualTo(columnDistance, rowDistance - 1);
        }
        return isEqualTo(columnDistance, rowDistance);
    }

    public boolean canBlackPawnMove(final int columnDistance, final int rowDistance, final boolean pawnAtInitial) {
        if (rowDistance == BLACK_PAWN_BEGINNING_DISTANCE && columnDistance == NOT_MOVE && this == S && pawnAtInitial) {
            return isEqualTo(columnDistance, rowDistance + 1);
        }
        return isEqualTo(columnDistance, rowDistance);
    }

    public boolean isEqualTo(final int columnDistance, final int rowDistance) {
        return column == columnDistance && row == rowDistance;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}

