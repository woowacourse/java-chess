package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class Bishop extends Piece {
    private static final int BISHOP_UNICODE_DECIMAL = 9815;

    public Bishop() {
        super();
    }

    @Override
    public boolean isMovable(final Position current, final Position destination,
                             final Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        final List<Position> diagonalPath = current.generateDiagonalPath(destination);
        return checkEmptyPath(diagonalPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        return current.checkDiagonal(destination);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int hashCode() {
        return BISHOP_UNICODE_DECIMAL;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
