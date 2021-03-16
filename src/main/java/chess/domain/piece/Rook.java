package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Piece {
    private static final String ROOK_INITIAL = "R";

    public Rook(Side side) {
        super(side, ROOK_INITIAL);
    }

    @Override
    public List<Position> movable(Position from, Position to) {
        return null;
    }
}
