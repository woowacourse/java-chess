package chess.domain.piece;

import chess.domain.position.Difference;
import chess.domain.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, Difference::hasSameDistance);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
