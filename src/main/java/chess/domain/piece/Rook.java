package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public class Rook extends SlidingPiece {

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Set<Position> movablePositionsFrom(final Position source) {
        return movablePositionsOf(Direction.ofRook(), source);
    }
}
