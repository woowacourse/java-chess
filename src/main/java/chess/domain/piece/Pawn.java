package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class Pawn extends Piece {

    private boolean isMoved = false;
    private final Rule catchRule;

    public Pawn(Color color) {
        super(color, decideMoveRule(color));
        catchRule = decideCatchRule(color);
    }

    private static Rule decideMoveRule(Color color) {
        if (color == Color.WHITE) {
            return (fileDifference, rankDifference) -> rankDifference.hasSameDifference(1)
                    && fileDifference.hasDistance(0);
        }
        return (fileDifference, rankDifference) -> rankDifference.hasSameDifference(-1)
                && fileDifference.hasDistance(0);
    }

    private Rule decideFirstMoveRule(Color color) {
        if (color == Color.WHITE) {
            return (fileDifference, rankDifference) -> rankDifference.hasSameDifference(2);
        }
        return (fileDifference, rankDifference) -> rankDifference.hasSameDifference(-2);
    }

    private Rule decideCatchRule(Color color) {
        if (color == Color.WHITE) {
            return (fileDifference, rankDifference) ->
                    rankDifference.hasDistance(1) &&
                            fileDifference.hasDistance(1);
        }
        return (fileDifference, rankDifference) ->
                rankDifference.hasSameDifference(-1) &&
                        fileDifference.hasDistance(1);
    }

    @Override
    public boolean isMovable(FileDifference fileDifference, RankDifference rankDifference) {
        if (!isMoved) {
            isMoved = true;
            return proceedFirstMove(fileDifference, rankDifference);
        }
        return super.isMovable(fileDifference, rankDifference);
    }

    private boolean proceedFirstMove(FileDifference fileDifference, RankDifference rankDifference) {
        if (super.isSameColor(Color.BLACK)) {
            return decideFirstMoveRule(Color.BLACK).obey(fileDifference, rankDifference)
                    || super.isMovable(fileDifference, rankDifference);
        }
        return decideFirstMoveRule(Color.WHITE).obey(fileDifference, rankDifference)
                || super.isMovable(fileDifference, rankDifference);
    }

    @Override
    public boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference) {
        return catchRule.obey(fileDifference, rankDifference);
    }
}
