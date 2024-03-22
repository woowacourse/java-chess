package chess.domain.piece;

import chess.domain.piece.rule.BlackPawnCatchRule;
import chess.domain.piece.rule.BlackPawnMoveRule;
import chess.domain.piece.rule.Rule;
import chess.domain.position.Position;
import chess.domain.position.PositionDifference;
import chess.domain.position.RankDifference;

public class BlackPawn extends Piece {

    private static final Rule catchRule = BlackPawnCatchRule.instance();

    private boolean isMoved = false;

    public BlackPawn() {
        super(Color.BLACK, BlackPawnMoveRule.instance());
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        if (!isMoved) {
            isMoved = true;
            return positionDifference.isObeyRule(decideFirstMoveRule()) || super.isMovable(from, to);
        }
        return super.isMovable(from, to);
    }

    private Rule decideFirstMoveRule() {
        return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(-2));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(catchRule);
    }
}
