package chess.model.piece;

import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.List;

public sealed abstract class SlidingPiece
        extends Piece
        permits Bishop, Rook, Queen {
    SlidingPiece(final Color color, Type type) {
        super(color, type);
    }

    @Override
    public List<Position> getIntermediatePositions(Movement movement) {
        return movement.getIntermediatePositions();
    }
}
