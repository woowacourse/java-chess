package chess.model.piece;

import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.List;

public sealed abstract class JumpingPiece
        extends Piece
        permits Knight, Pawn, King, Empty {
    JumpingPiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    public List<Position> getIntermediatePositions(Movement movement) {
        return List.of();
    }
}
