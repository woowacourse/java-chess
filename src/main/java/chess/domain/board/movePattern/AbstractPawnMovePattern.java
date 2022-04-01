package chess.domain.board.movePattern;

import chess.domain.position.Position;
import chess.domain.board.Color;

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
