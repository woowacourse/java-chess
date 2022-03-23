package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Row;

public class BlackPawnMoveStrategy implements MoveStrategy {

    public static final Row BLACK_PAWN_START_ROW = Row.RANK_7;

    @Override
    public boolean canMove(Position src, Position dest) {
        if (dest.equals(src.move(0, -1))) {
            return true;
        }

        return src.isStartRow(BLACK_PAWN_START_ROW) && dest.equals(src.move(0, -2));
    }
}
