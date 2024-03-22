package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public class Bishop extends SlidingPiece {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Set<Position> movablePositionsFrom(final Position source) {
        return movablePositionsOf(Direction.ofBishop(), source);
    }
}
