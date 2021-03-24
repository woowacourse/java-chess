package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

import static chess.domain.team.BlackTeam.BLACK_PAWN_COLUMN;
import static chess.domain.team.WhiteTeam.WHITE_PAWN_COLUMN;

public class Pawn extends Piece {
    private static final int UNICODE_DECIMAL = 9817;
    public static final double SCORE_PAWN = 1.0;
    public static final int DIRECTION_WHITE = 1;
    public static final int DIRECTION_BLACK = -1;

    private final int direction;

    public Pawn(final int direction) {
        this.direction = direction;
    }

    @Override
    public boolean isMovable(final Position current, final Position destination, final Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }

        if (current.checkDiagonalRule(destination)) {
            return chessBoard.containsKey(destination);
        }

        return checkStraightEmptyPath(current, destination, chessBoard);
    }


    private boolean checkStraightEmptyPath(Position current, Position destination, Map<Position, Piece> chessBoard) {
        final List<Position> straightPath = current.generateStraightPath(destination);
        return checkEmptyPath(straightPath, chessBoard) && !chessBoard.containsKey(destination);
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        if (isFirstMoved(current)) {
            return checkPositionRuleAfterMove(current, destination);
        }

        return checkPositionRuleFirstMove(current, destination);
    }

    private boolean isFirstMoved(Position current) {
        if (direction == DIRECTION_BLACK) {
            return current.getY() != BLACK_PAWN_COLUMN;
        }

        if (direction == DIRECTION_WHITE) {
            return current.getY() != WHITE_PAWN_COLUMN;
        }

        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private boolean checkPositionRuleAfterMove(final Position current, final Position destination) {
        if (current.moveXandY(0, direction).equals(destination)) {
            return true;
        }
        return current.checkDiagonalToDirection(destination, direction);
    }

    private boolean checkPositionRuleFirstMove(final Position current, final Position destination) {
        if (current.moveXandY(0, direction).equals(destination) ||
                current.moveXandY(0, direction * 2).equals(destination)) {
            return true;
        }

        return current.checkDiagonalToDirection(destination, direction);
    }

    @Override
    public int hashCode() {
        return UNICODE_DECIMAL * direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
