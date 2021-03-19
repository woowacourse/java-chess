package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class King extends Piece {
    private static final int UNICODE_DECIMAL = 9812;

    public King() {
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        return checkPositionRule(current, destination);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return current.checkAdjacentEightWay(destination);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public int hashCode() {
        return UNICODE_DECIMAL;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() == obj.getClass()) return true;
        return false;
    }
}
