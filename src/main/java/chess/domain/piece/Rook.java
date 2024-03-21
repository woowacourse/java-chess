package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public class Rook extends SlidingPiece {
    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        return movableSquaresOf(Direction.ofRook(), source);
    }
}
