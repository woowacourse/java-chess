package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Pawn extends Piece {
    private int startPoint;

    public Pawn(PlayerType playerType) {
        super(playerType);
        this.startPoint = (playerType == PlayerType.WHITE) ? 6 : 1;
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        int directionCorrection = isWhite() ? -1 : 1;
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

    @Override
    public String pieceToString() {
        return isWhite() ? "p" : "P";
    }
}
