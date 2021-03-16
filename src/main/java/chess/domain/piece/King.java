package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class King extends Piece{
    private static final String KING_INITIAL = "K";

    public King(Side side) {
        super(side, KING_INITIAL);
    }

    @Override
    public List<Position> movable(Position from, Position to) {
        return null;
    }
}
