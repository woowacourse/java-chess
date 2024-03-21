package chess.domain.piece;

import chess.domain.Rule;
import chess.domain.position.Position;
import chess.domain.position.FileDifference;
import chess.domain.position.PositionDifference;
import chess.domain.position.RankDifference;

public class Queen extends Piece {

    private final Rule moveRule;

    public Queen(Color color) {
        super(color);
        moveRule = (fileDifference, rankDifference) ->
                ((!fileDifference.equals(new FileDifference(0)) && rankDifference.equals(new RankDifference(0))) ||
                        (fileDifference.equals(new FileDifference(0)) && !rankDifference.equals(new RankDifference(0))))
                        || fileDifference.hasSameDistance(rankDifference);
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
