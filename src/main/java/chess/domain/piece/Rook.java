package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class Rook extends Piece {
    private static final int ROOK_HASHCODE_AS_UNICODE = 9814;

    public Rook(final boolean isFirstMove) {
        super(isFirstMove);
    }

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
        return ROOK_HASHCODE_AS_UNICODE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
