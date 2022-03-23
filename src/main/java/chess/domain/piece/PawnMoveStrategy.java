package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.board.Row;

import java.util.List;

public abstract class PawnMoveStrategy implements MoveStrategy {

    public static MoveStrategy of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMoveStrategy();
        }
        if (color == Color.WHITE) {
            return new WhitePawnMoveStrategy();
        }
        throw new IllegalArgumentException("error");
    }

    @Override
    public final boolean canMove(Position src, Position dest) {
        List<Direction> directions = Direction.getPawnDirections();

        for (Direction direction : directions) {
            int x = direction.getX() * blackWeight();
            int y = direction.getY() * blackWeight();

            if (dest.equals(src.move(x, y))) {
                return true;
            }
            if (src.isStartRow(startRow()) && dest.equals(src.move(x * 2, y * 2))) {
                return true;
            }
        }
        return false;
    }

    public abstract Row startRow();

    public abstract int blackWeight();
}
