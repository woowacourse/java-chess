package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Row;
import java.util.List;

public final class BlackPawnMovePattern extends AbstractPawnMovePattern {

    private static final Row BLACK_PAWN_START_ROW = Row.RANK_7;

    @Override
    Row getStartRow() {
        return BLACK_PAWN_START_ROW;
    }

    @Override
    List<Direction> getDirections() {
        return Direction.getBlackPawnDirections();
    }
}
