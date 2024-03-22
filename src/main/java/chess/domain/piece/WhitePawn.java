package chess.domain.piece;

import chess.domain.piece.rule.Rule;
import chess.domain.piece.rule.WhitePawnCatchRule;
import chess.domain.piece.rule.WhitePawnMoveRule;
import chess.domain.position.Position;
import chess.domain.position.PositionDifference;
import chess.domain.position.RankDifference;

public class WhitePawn extends Piece {

    private static final Rule catchRule = WhitePawnCatchRule.instance();

    private boolean isMoved = false;

    public WhitePawn() {
        super(Color.WHITE, WhitePawnMoveRule.instance());
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        if (!isMoved) {
            isMoved = true;
            return positionDifference.isObeyRule(firstMoveRule()) || super.isMovable(from, to);
        }
        return super.isMovable(from, to);
    }

    private Rule firstMoveRule() {
        return (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(2));
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(catchRule);
    }
}
