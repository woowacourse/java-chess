package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
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
    public boolean isMovable(Position from, Position to) {
        if (!isMoved) {
            isMoved = true;
            return proceedFirstMove(from, to);
        }
        return super.isMovable(from, to);
    }

    private boolean proceedFirstMove(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        if (super.isSameColor(Color.BLACK)) {
            return decideFirstMoveRule(Color.BLACK).obey(fileDifference, rankDifference)
                    || super.isMovable(from, to);
        }
        return decideFirstMoveRule(Color.WHITE).obey(fileDifference, rankDifference)
                || super.isMovable(from, to);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return catchRule.obey(fileDifference, rankDifference);
    }
}
