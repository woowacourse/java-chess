package chess.domain.piece;

import chess.domain.piece.rule.RookMoveRule;
import chess.domain.position.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, RookMoveRule.instance());
    }

    public static Rook of(Color color) {
        return new Rook(color);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
