package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Rook extends Piece {

    public Rook(PlayerType playerType) {
        super(playerType);
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 0) {
            return true;
        }
        if (gradient == Double.MAX_VALUE) {
            return true;
        }
        return false;
    }

    @Override
    public String pieceToString() {
        return isWhite() ? "r" : "R";
    }
}
