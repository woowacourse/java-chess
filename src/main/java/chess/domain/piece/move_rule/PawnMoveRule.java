package chess.domain.piece.move_rule;

import chess.domain.piece.Color;

public abstract class PawnMoveRule implements MoveRule {

    protected PawnMoveRule() {}

    public static PawnMoveRule from(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMoveRule();
        }
        return new WhitePawnMoveRule();
    }

    @Override
    public boolean isPawnMove() {
        return true;
    }

    @Override
    public boolean isKingMove() {
        return false;
    }
}
