package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class Pawn extends Piece {
    private static final int PAWN_HASHCODE_AS_UNICODE = 9817;

    private final int direction;

    public Pawn(final boolean isFirstMove, final int direction) {
        super(isFirstMove);
        this.direction = direction;
    }

    public Pawn(final int direction) {
        this.direction = direction;
    }

    @Override
    public boolean isMovable(final Position current, final Position destination,
                             final Map<Position, Piece> chessBoard) {
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
        return PAWN_HASHCODE_AS_UNICODE * direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return direction == pawn.direction;
    }
}
