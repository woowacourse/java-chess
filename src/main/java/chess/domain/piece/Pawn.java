package chess.domain.piece;

import chess.domain.Rule;
import chess.domain.position.Position;
import chess.domain.position.FileDifference;
import chess.domain.position.PositionDifference;
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
            return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(1));
        }
        return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(-1));
    }

    private Rule decideFirstMoveRule(Color color) {
        if (color == Color.WHITE) {
            return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(2));
        }
        return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(-2));
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
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        if (!isMoved) {
            isMoved = true;
            if (super.isSameColor(Color.BLACK)) {
                return positionDifference.isObeyRule(decideFirstMoveRule(Color.BLACK)) || super.isMovable(from, to);
            }
            return positionDifference.isObeyRule(decideFirstMoveRule(Color.WHITE)) || super.isMovable(from, to);
        }
        return super.isMovable(from, to);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(catchRule);
    }
}
