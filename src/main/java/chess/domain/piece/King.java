package chess.domain.piece;

import chess.domain.piece.rule.KingMoveRule;
import chess.domain.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color, KingMoveRule.instance());
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
