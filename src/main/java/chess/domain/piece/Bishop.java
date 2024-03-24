package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Set;

public class Bishop extends SlidingPiece {

    public Bishop(final Color color, final Square square) {
        super(color, PieceType.BISHOP, square);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        return movableSquaresOf(Direction.ofBishop(), source);
    }
}
