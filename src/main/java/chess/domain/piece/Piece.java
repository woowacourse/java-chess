package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {
    protected boolean isMoved = false;

    public Piece() {
    }

    public abstract boolean isMovable(final Position current, final Position destination, final Map<Position, Piece> chessBoard);

    public abstract boolean checkPositionRule(final Position current, final Position destination);

    public final boolean checkEmptyPath(final List<Position> path, final Map<Position, Piece> chessBoard) {
        for (Position position : path) {
            if (chessBoard.containsKey(position)) {
                return false;
            }
        }
        return true;
    }

    public void isMoved() {
        this.isMoved = true;
    }
}
