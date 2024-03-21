package chess.piece;

import chess.Rule;
import chess.position.Position;
import chess.position.PositionDifference;

public class Knight extends Piece {

    private final Rule moveRule;

    public Knight(Color color) {
        super(color);
        moveRule = ((fileDifference, rankDifference) -> (fileDifference.hasDistance(1) && rankDifference.hasDistance(2))
                || (fileDifference.hasDistance(2) && rankDifference.hasDistance(1)));
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
