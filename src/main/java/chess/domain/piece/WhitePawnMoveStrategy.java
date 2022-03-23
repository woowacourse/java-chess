package chess.domain.piece;

import chess.domain.board.Row;

public class WhitePawnMoveStrategy extends PawnMoveStrategy {

    private static final Row WHITE_PAWN_START_ROW = Row.RANK_2;

    public Row startRow() {
        return WHITE_PAWN_START_ROW;
    }

    public int blackWeight() {
        return 1;
    }
}
