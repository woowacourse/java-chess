package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color, decideRule());
    }

    private static Rule decideRule() {
        return (fileDifference, rankDifference) -> (fileDifference.hasDistance(1) || fileDifference.hasDistance(0))
                && (rankDifference.hasDistance(1) || rankDifference.hasDistance(0));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
