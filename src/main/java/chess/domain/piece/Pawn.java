package chess.domain.piece;

import chess.domain.Rule;
import chess.domain.position.Position;
import chess.domain.position.FileDifference;
import chess.domain.position.PositionDifference;
import chess.domain.position.RankDifference;

public class Pawn extends Piece {

    private final Rule catchRule;

    public Pawn(Color color) {
        super(color, decideMoveRule(color));
        catchRule = decideCatchRule(color);
    }

    private static Rule decideMoveRule(Color color) {
        if (color == Color.WHITE) {
            return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(1));
        }
        return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(-1));
    }

    private Rule decideCatchRule(Color color) {
        if (color == Color.WHITE) {
            return (fileDifference, rankDifference) ->
                    rankDifference.equals(new RankDifference(1)) &&
                            (fileDifference.equals(new FileDifference(-1)) || fileDifference.equals(
                                    new FileDifference(1)));
        }
        return (fileDifference, rankDifference) ->
                rankDifference.equals(new RankDifference(-1)) &&
                        (fileDifference.equals(new FileDifference(-1)) || fileDifference.equals(new FileDifference(1)));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(catchRule);
    }
}
