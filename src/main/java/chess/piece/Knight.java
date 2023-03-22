package chess.piece;

import chess.board.Position;

public class Knight extends NonSlidingPiece {

    public Knight(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final double slope = position.getSlope(targetPosition);

        return slope == 0.5 || slope == 2.0;
    }

    @Override
    public Knight move(final Position positionToMove) {
        return new Knight(positionToMove, this.side);
    }
}
