package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, decideRule());
    }

    private static Rule decideRule() {
        return ((fileDifference, rankDifference) -> (fileDifference.hasDistance(1) && rankDifference.hasDistance(2))
                || (fileDifference.hasDistance(2) && rankDifference.hasDistance(1)));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
