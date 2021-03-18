package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class Knight extends Piece {
    private static final int UNICODE_DECIMAL = 9816;

    public Knight() {
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        return checkPositionRule(current, destination);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        int xDiff = Math.abs(current.getX() - destination.getX());
        int yDiff = Math.abs(current.getY() - destination.getY());
        return ((xDiff == 1 && yDiff == 2) || (xDiff == 2 && yDiff == 1));
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
