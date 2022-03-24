package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public abstract class AbstractPawnMovePattern {

    public static AbstractPawnMovePattern of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMovePattern();
        }
        if (color == Color.WHITE) {
            return new WhitePawnMovePattern();
        }
        throw new IllegalArgumentException("error");
    }

    public final boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    public abstract Direction findDirection(Position src, Position dest);

    public abstract List<Direction> getDirections();
}
