package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Knight extends Piece {

    public Knight(PlayerType playerType) {
        super(playerType);
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        return isNotStraight(gradient) && isThreeStep(Math.abs(next.xDistance(prev)) + Math.abs(next.yDistance(prev)));
    }

    private boolean isNotStraight(double gradient) {
        return gradient != 0 && gradient != Double.MAX_VALUE;
    }

    private boolean isThreeStep(int distanceSum) {
        return distanceSum == 3;
    }


    @Override
    public String pieceToString() {
        return isWhite() ? "n" : "N";
    }
}
