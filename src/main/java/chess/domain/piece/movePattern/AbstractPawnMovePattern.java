package chess.domain.piece.movePattern;

import chess.domain.board.Position;
import chess.domain.piece.Color;

public abstract class AbstractPawnMovePattern implements MovePattern {

    public static AbstractPawnMovePattern of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMovePattern();
        }
        if (color == Color.WHITE) {
            return new WhitePawnMovePattern();
        }
        throw new IllegalArgumentException("error");
    }

    @Override
    public final boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }
}
