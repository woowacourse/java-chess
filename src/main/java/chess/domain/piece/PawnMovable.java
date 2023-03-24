package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.Collections;
import java.util.List;

public abstract class PawnMovable extends Piece {
    private static final int ROW_MOVE_ZERO = 0;
    private static final int ROW_MOVE_ONCE = 1;
    private static final int COLUMN_MOVE_TWICE_BLACK = -2;
    private static final int COLUMN_MOVE_TWICE_WHITE = 2;
    private static final int COLUMN_MOVE_ONCE_BLACK = -1;
    private static final int COLUMN_MOVE_ONCE_WHITE = 1;

    protected PawnMovable(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    protected final List<Position> createMovablePositions(final Position source, final Position target) {
        final int rowDiff = Math.abs(source.diff(target.getRow()));
        final int columnDiff = source.diff(target.getColumn());

        if (isBlackMovable(source, rowDiff, columnDiff) || isWhiteMovable(source, rowDiff, columnDiff)) {
            return List.of(target);
        }

        return Collections.emptyList();
    }

    private boolean isBlackMovable(final Position source, final int rowDiff, final int columnDiff) {
        return color.isBlack()
                && (isBlackMoveSouthOnce(rowDiff, columnDiff)
                || isBlackMoveSouthTwice(source, rowDiff, columnDiff)
                || isBlackMoveDiagonalOnce(rowDiff, columnDiff));
    }

    private boolean isBlackMoveSouthOnce(final int rowDiff, final int columnDiff) {
        return rowDiff == ROW_MOVE_ZERO && columnDiff == COLUMN_MOVE_ONCE_BLACK;
    }

    private boolean isBlackMoveSouthTwice(final Position source, final int rowDiff, final int columnDiff) {
        return source.getColumn() == Column.COLUMN_1 && rowDiff == ROW_MOVE_ZERO && columnDiff == COLUMN_MOVE_TWICE_BLACK;
    }

    private boolean isBlackMoveDiagonalOnce(final int rowDiff, final int columnDiff) {
        return rowDiff == ROW_MOVE_ONCE && columnDiff == COLUMN_MOVE_ONCE_BLACK;
    }

    private boolean isWhiteMovable(final Position source, final int rowDiff, final int columnDiff) {
        return color.isWhite()
                && (isWhiteMoveNorthOnce(rowDiff, columnDiff)
                || isWhiteMoveNorthTwice(source, rowDiff, columnDiff)
                || isWhiteMoveDiagonalOnce(rowDiff, columnDiff));
    }

    private boolean isWhiteMoveNorthOnce(final int rowDiff, final int columnDiff) {
        return rowDiff == ROW_MOVE_ZERO && columnDiff == COLUMN_MOVE_ONCE_WHITE;
    }

    private boolean isWhiteMoveNorthTwice(final Position source, final int rowDiff, final int columnDiff) {
        return source.getColumn() == Column.COLUMN_6 && rowDiff == ROW_MOVE_ZERO && columnDiff == COLUMN_MOVE_TWICE_WHITE;
    }

    private boolean isWhiteMoveDiagonalOnce(final int rowDiff, final int columnDiff) {
        return rowDiff == ROW_MOVE_ONCE && columnDiff == COLUMN_MOVE_ONCE_WHITE;
    }
}
