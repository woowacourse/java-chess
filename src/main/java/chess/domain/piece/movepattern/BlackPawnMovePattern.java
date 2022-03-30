package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Rank;
import java.util.List;

public final class BlackPawnMovePattern extends AbstractPawnMovePattern {

    private static final Rank BLACK_PAWN_START_RANK = Rank.RANK_7;

    @Override
    Rank getStartRow() {
        return BLACK_PAWN_START_RANK;
    }

    @Override
    List<Direction> getDirections() {
        return Direction.getBlackPawnDirections();
    }
}
