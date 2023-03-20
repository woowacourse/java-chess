package chess.piece.sliding;

import chess.Position;
import chess.piece.Color;
import chess.piece.Direction;
import chess.piece.Piece;
import java.util.Set;

public final class Queen extends SlidingPiece {

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected Set<Direction> directions() {
        return Direction.ofEvery();
    }

    @Override
    protected Piece update(final Position destination) {
        return new Queen(color(), destination);
    }
}
