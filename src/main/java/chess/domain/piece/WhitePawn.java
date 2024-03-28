package chess.domain.piece;

import chess.domain.piece.rule.Rule;
import chess.domain.piece.rule.WhitePawnCatchRule;
import chess.domain.piece.rule.WhitePawnMoveRule;
import chess.domain.position.Position;
import chess.domain.position.PositionDifference;
import chess.domain.position.Rank;
import chess.domain.position.RankDifference;

public class WhitePawn extends Piece {

    private static final Rule catchRule = WhitePawnCatchRule.instance();

    private static final Rank initialRank = Rank.TWO;

    public WhitePawn() {
        super(Color.WHITE, WhitePawnMoveRule.instance());
    }

    public static WhitePawn of(Color color) {
        return new WhitePawn();
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);

        if (from.isSameRank(initialRank)) {
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

    @Override
    public boolean isPawn() {
        return true;
    }
}
