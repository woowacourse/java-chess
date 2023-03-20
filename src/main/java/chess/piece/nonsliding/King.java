package chess.piece.nonsliding;

import chess.Position;
import chess.piece.Color;
import chess.piece.Direction;
import chess.piece.Piece;
import java.util.Set;

public final class King extends NonSlidingPiece {

    public King(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected Set<Direction> directions() {
        return Direction.ofEvery();
    }

    @Override
    protected Piece update(final Position destination) {
        return new King(color(), destination);
    }
}
