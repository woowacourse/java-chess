package chess.piece;

import chess.board.Position;
import java.util.Collections;
import java.util.List;

public abstract class NonSlidingPiece extends Piece {

    public NonSlidingPiece(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(final Position targetPosition) {
        return false;
    }

    @Override
    public Piece move(final Position positionToMove) {
        return null;
    }

    public List<Position> getPaths(Position targetPosition) {
        return Collections.emptyList();
    }
}
