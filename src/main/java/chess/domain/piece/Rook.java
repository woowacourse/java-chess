package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, decideRule());
    }

    private static Rule decideRule() {
        return (fileDifference, rankDifference) ->
                ((!fileDifference.hasDistance(0) && rankDifference.hasDistance(0))) ||
                        ((fileDifference.hasDistance(0)) && !rankDifference.hasDistance(0));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
