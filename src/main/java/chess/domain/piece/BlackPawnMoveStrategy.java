package chess.domain.piece;

import chess.domain.board.Row;

public class BlackPawnMoveStrategy extends PawnMoveStrategy {

    private static final Row BLACK_PAWN_START_ROW = Row.RANK_7;

    public Row startRow() {
        return BLACK_PAWN_START_ROW;
    }

    public int blackWeight() {
        return -1;
    }
}
