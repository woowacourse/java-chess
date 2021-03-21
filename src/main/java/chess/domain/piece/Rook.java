package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class Rook extends Piece {
    private static final int ROOK_UNICODE_DECIMAL = 9814;

    public Rook() {
    }

    @Override
    public boolean isMovable(final Position current, final Position destination,
                             final Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        final List<Position> straightPath = current.generateStraightPath(destination);
        return checkEmptyPath(straightPath, chessBoard);
    }

    @Override
    public boolean isCastlingMovable(final Position current, final Position destination,
                                     final Map<Position, Piece> chessBoard) {
        return false;
    }

    @Override
    public boolean isPromotionMovable(final Position current, final Position destination,
                                      final Map<Position, Piece> chessBoard) {
        return false;
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        return current.checkStraight(destination);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int hashCode() {
        return ROOK_UNICODE_DECIMAL;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
