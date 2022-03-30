package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Row;
import java.util.List;

public final class WhitePawnMovePattern extends AbstractPawnMovePattern {

    private static final Row WHITE_PAWN_START_ROW = Row.RANK_2;

    @Override
    Row getStartRow() {
        return WHITE_PAWN_START_ROW;
    }

    @Override
    List<Direction> getDirections() {
        return Direction.getWhitePawnDirections();
    }
}
