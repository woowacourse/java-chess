package chess.domain.piece;

import chess.domain.piece.rule.KnightMoveRule;
import chess.domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, KnightMoveRule.instance());
    }

    public static Knight of(Color color) {
        return new Knight(color);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
