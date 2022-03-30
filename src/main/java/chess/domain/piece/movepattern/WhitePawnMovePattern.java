package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Rank;
import java.util.List;

public final class WhitePawnMovePattern extends AbstractPawnMovePattern {

    private static final Rank WHITE_PAWN_START_RANK = Rank.RANK_2;

    @Override
    Rank getStartRow() {
        return WHITE_PAWN_START_RANK;
    }

    @Override
    List<Direction> getDirections() {
        return Direction.getWhitePawnDirections();
    }
}
