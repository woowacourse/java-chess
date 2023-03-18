package chess.piece.special;

import chess.board.Position;
import chess.piece.Piece;
import chess.piece.Side;

import java.util.Collections;
import java.util.List;

public class Knight extends SpecialPiece {

    public Knight(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final double slope = position.getSlope(targetPosition);

        return slope == 0.5 || slope == 2.0;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Knight(positionToMove, this.side);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        return Collections.emptyList();
    }
}
