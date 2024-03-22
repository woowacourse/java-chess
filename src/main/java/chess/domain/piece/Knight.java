package chess.domain.piece;

import chess.domain.piece.rule.KnightMoveRule;
import chess.domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, KnightMoveRule.instance());
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
