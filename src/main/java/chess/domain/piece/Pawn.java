package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class Pawn implements Piece {
    private PlayerType playerType;
    private int startPoint;

    public Pawn(PlayerType playerType) {
        this.playerType = playerType;
        this.startPoint = (playerType == PlayerType.WHITE) ? 6 : 1;
    }

    public boolean isMovable(Point prev, Point next) {
        int directionCorrection = playerType == PlayerType.WHITE ? -1 : 1;
        if (Math.abs(next.xDistance(prev)) > 1) {
            return false;
        }
        if (next.yDistance(prev) * directionCorrection > 1 && prev.getY() != startPoint) {
            return false;
        }
        if (next.yDistance(prev) * directionCorrection < 1) {
            return false;
        }
        return true;
    }
}
