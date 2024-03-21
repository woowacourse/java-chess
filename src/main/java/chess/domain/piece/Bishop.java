package chess.domain.piece;

import chess.domain.Rule;
import chess.domain.position.Difference;
import chess.domain.position.Position;
import chess.domain.position.PositionDifference;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, Difference::hasSameDistance);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
