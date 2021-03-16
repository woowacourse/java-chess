package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece{
    private static final String PAWN_INITIAL = "P";

    public Pawn(Side side) {
        super(side, PAWN_INITIAL);
    }

    @Override
    public List<Position> movable(Position from, Position to) {
        return null;
    }
}
