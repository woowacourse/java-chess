package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Queen extends Piece {
    private static final int UNICODE_DECIMAL = 9813;
    public static final double SCORE_QUEEN = 9.0;

    public Queen() {
    }

    @Override
    public boolean isMovable(final Position current, final Position destination, final Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }

        return checkDiagonalOrStraightRule(current, destination, chessBoard);
    }

    private boolean checkDiagonalOrStraightRule(final Position current, final Position destination, final Map<Position, Piece> chessBoard) {
        if (current.checkDiagonalRule(destination)) {
            return checkDiagonalEmptyPath(current, destination, chessBoard);
        }

        if (current.checkStraightRule(destination)) {
            return checkStraightEmptyPath(current, destination, chessBoard);
        }
        return false;
    }

    private boolean checkDiagonalEmptyPath(final Position current, final Position destination, final Map<Position, Piece> chessBoard) {
        final List<Position> diagonalPath = current.generateDiagonalPath(destination);
        return checkEmptyPath(diagonalPath, chessBoard);
    }

    private boolean checkStraightEmptyPath(final Position current, final Position destination, final Map<Position, Piece> chessBoard) {
        final List<Position> straightPath = current.generateStraightPath(destination);
        return checkEmptyPath(straightPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return current.checkDiagonalRule(destination) || current.checkStraightRule(destination);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int hashCode() {
        return UNICODE_DECIMAL;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return getClass() == obj.getClass();
    }
}