package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    private final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    protected abstract List<Position> movable(Position from, Position to);
}
