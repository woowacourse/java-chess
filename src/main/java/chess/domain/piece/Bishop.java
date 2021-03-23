package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Bishop extends Piece {
    private static final int UNICODE_DECIMAL = 9815;
    public static final double SCORE_BISHOP = 3.0;

    public Bishop() {
        super();
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }

        final List<Position> diagonalPath = current.generateDiagonalPath(destination);

        return checkEmptyPath(diagonalPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return current.checkDiagonalRule(destination);
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
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
