package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Row;

public class WhitePawnMoveStrategy implements MoveStrategy {

    private static final Row WHITE_PAWN_START_ROW = Row.RANK_2;

    @Override
    public boolean canMove(Position src, Position dest) {

        if (dest.equals(src.move(0, 1))) {
            return true;
        }
        return src.isStartRow(WHITE_PAWN_START_ROW) && dest.equals(src.move(0, 2));
    }
}
