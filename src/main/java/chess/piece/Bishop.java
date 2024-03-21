package chess.piece;

import chess.Rule;
import chess.position.Difference;
import chess.position.Position;
import chess.position.PositionDifference;

public class Bishop extends Piece {

    private final Rule moveRule;

    public Bishop(Color color) {
        super(color);
        moveRule = Difference::hasSameDistance;
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(moveRule);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
