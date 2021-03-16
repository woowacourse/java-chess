package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece{
    protected Queen(Side side) {
        super(side);
    }

    @Override
    protected List<Position> movable(Position from, Position to) {
        return null;
    }
}
