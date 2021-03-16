package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(Side.NONE);
    }

    @Override
    protected List<Position> movable(Position from, Position to) {
        throw new UnsupportedOperationException();
    }
}
