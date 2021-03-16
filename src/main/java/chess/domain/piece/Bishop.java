package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Bishop extends Piece{
    private static final String BISHOP_INITIAL = "B";

    public Bishop(Side side) {
        super(side, BISHOP_INITIAL);
    }

    @Override
    public List<Position> movable(Position from, Position to) {
        return null;
    }
}
