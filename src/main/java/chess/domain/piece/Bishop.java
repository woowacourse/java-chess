package chess.domain.piece;

import chess.domain.piece.rule.BishopMoveRule;
import chess.domain.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, BishopMoveRule.instance());
    }

    public static Bishop of(Color color) {
        return new Bishop(color);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
