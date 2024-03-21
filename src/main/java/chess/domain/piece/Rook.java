package chess.domain.piece;

import chess.domain.Rule;
import chess.domain.position.Position;
import chess.domain.position.FileDifference;
import chess.domain.position.PositionDifference;
import chess.domain.position.RankDifference;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, decideRule());
    }

    private static Rule decideRule() {
        return (fileDifference, rankDifference) ->
                (!fileDifference.equals(new FileDifference(0)) && rankDifference.equals(new RankDifference(0))) ||
                        (fileDifference.equals(new FileDifference(0)) && !rankDifference.equals(new RankDifference(0)));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
