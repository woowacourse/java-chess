package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public final class Knight extends Piece {
    private static final int KNIGHT_HASHCODE_AS_UNICODE = 9816;

    public Knight(final boolean isFirstMove) {
        super(isFirstMove);
    }

    public Knight() {
    }

    @Override
    public boolean isMovable(final Position current, final Position destination,
                             final Map<Position, Piece> chessBoard) {
        return checkPositionRule(current, destination);
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        return current.checkKnightMoveRule(destination);
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
        return KNIGHT_HASHCODE_AS_UNICODE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
