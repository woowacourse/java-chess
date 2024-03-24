package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Set;

public class Rook extends SlidingPiece {
    public Rook(final Color color, Square square) {
        super(color, PieceType.ROOK, square);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        return movableSquaresOf(Direction.ofRook(), source);
    }
}
