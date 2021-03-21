package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class Pawn extends Piece {
    private static final int PAWN_UNICODE_DECIMAL = 9817;

    private final int direction;

    public Pawn(final int direction) {
        this.direction = direction;
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        if (current.checkDiagonal(destination)) {
            return chessBoard.containsKey(destination);
        }
        final List<Position> straightPath = current.generateStraightPath(destination);
        return checkEmptyPath(straightPath, chessBoard) && !chessBoard.containsKey(destination);
    }

    @Override
    public boolean isCastlingMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        return false;
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        if (isFirstMove()) {
            return checkPositionRuleFirstMove(current, destination);
        }
        return checkPositionRuleAfterMove(current, destination);
    }

    private boolean checkPositionRuleFirstMove(final Position current, final Position destination) {
        if (current.moveXandY(0, direction).equals(destination) ||
                current.moveXandY(0, direction * 2).equals(destination)) {
            return true;
        }
        return current.checkAdjacentDiagonalToDirection(destination, direction);
    }

    private boolean checkPositionRuleAfterMove(final Position current, final Position destination) {
        if (current.moveXandY(0, direction).equals(destination)) {
            return true;
        }
        return current.checkAdjacentDiagonalToDirection(destination, direction);
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
        return true;
    }

    @Override
    public int hashCode() {
        return PAWN_UNICODE_DECIMAL * direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
