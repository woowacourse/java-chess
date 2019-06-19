package chess.domain.piece;

import chess.domain.board.Point;

public class Pawn {
    private boolean isFirst;

    public Pawn() {
        this.isFirst = true;
    }

    public boolean isMovable(Point prev, Point next) {
        if (prev.getY() == 1) {
            if (next.yDistance(prev) > 2) {
                return false;
            }
            if (next.xDistance(prev) > 1) {
                return false;
            }
        }

        if (next.yDistance(prev) > 1) {
            return false;
        }

        if (next.xDistance(prev) > 1) {
            return false;
        }

        return true;
    }
}
