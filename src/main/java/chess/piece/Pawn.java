package chess.piece;

import chess.Rule;
import chess.position.FileDifference;
import chess.position.Position;
import chess.position.PositionDifference;
import chess.position.RankDifference;

public class Pawn extends Piece {

    private final Rule moveRule;
    private final Rule catchRule;

    public Pawn(Color color) {
        super(color);
        moveRule = decideMoveRule(color);
        catchRule = decideCatchRule(color);
    }

    private Rule decideMoveRule(Color color) {
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
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(moveRule);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(catchRule);
    }
}
