package chess.piece;

import chess.Rule;
import chess.position.Position;
import chess.position.PositionDifference;

public class King extends Piece {

    private final Rule moveRule;

    public King(Color color) {
        super(color);
        moveRule = (fileDifference, rankDifference) -> (fileDifference.hasDistance(1) || fileDifference.hasDistance(0))
                && (rankDifference.hasDistance(1) || rankDifference.hasDistance(0));
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
