package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {
    public Piece() {
    }

    public abstract boolean isMovable(final Position current, final Position destination, final Map<Position, Piece> chessBoard);

    public abstract boolean checkPositionRule(final Position current, final Position destination);

    public final boolean checkDiagonalRule(final Position current, final Position destination) {
        final int xDiff = Math.abs(current.getX() - destination.getX());
        final int yDiff = Math.abs(current.getY() - destination.getY());
        if (xDiff == yDiff) {
            return true;
        }
        return false;
    }

    public final boolean checkStraightRule(final Position current, final Position destination) {
        if (current.getX() == destination.getX() && current.getY() != destination.getY()) {
            return true;
        }
        if (current.getY() == destination.getY() && current.getX() != destination.getX()) {
            return true;
        }
        return false;
    }

    public final boolean checkEmptyPath(final List<Position> path, final Map<Position, Piece> chessBoard) {
        for (Position position : path) {
            if (chessBoard.containsKey(position)) {
                return false;
            }
        }
        return true;
    }
}
