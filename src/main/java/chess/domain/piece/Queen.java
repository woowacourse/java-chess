package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class Queen extends Piece {
    private static final int QUEEN_HASHCODE_AS_UNICODE = 9813;

    public Queen(final boolean isFirstMove) {
        super(isFirstMove);
    }

    public Queen() {
    }

    @Override
    public boolean isMovable(final Position current, final Position destination,
                             final Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        if (current.checkDiagonal(destination)) {
            final List<Position> diagonalPath = current.generateDiagonalPath(destination);
            return checkEmptyPath(diagonalPath, chessBoard);
        }
        final List<Position> straightPath = current.generateStraightPath(destination);
        return checkEmptyPath(straightPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        return current.checkDiagonal(destination) || current.checkStraight(destination);
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
        return QUEEN_HASHCODE_AS_UNICODE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
