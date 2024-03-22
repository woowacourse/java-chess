package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public class Queen extends SlidingPiece {

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public Set<Position> movablePositionsFrom(final Position source) {
        return movablePositionsOf(Direction.all(), source);
    }
}
