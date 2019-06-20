package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Pawn implements Piece {
    private PlayerType playerType;

    public Pawn(PlayerType playerType) {
        this.playerType = playerType;
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
