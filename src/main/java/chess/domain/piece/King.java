package chess.domain.piece;

import chess.domain.piece.rule.KingMoveRule;
import chess.domain.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color, KingMoveRule.instance());
    }

    public static King of(Color color) {
        return new King(color);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
