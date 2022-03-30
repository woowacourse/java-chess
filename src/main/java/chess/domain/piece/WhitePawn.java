package chess.domain.piece;

import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;

import chess.domain.board.Direction;
import chess.domain.board.Rank;
import java.util.List;

public final class WhitePawn extends Pawn {

    private static final List<Direction> directions = List.of(NORTH, NORTH_EAST, NORTH_WEST);
    private static final Rank WHITE_PAWN_START_RANK = Rank.RANK_2;

    WhitePawn(Color color) {
        super(color, directions);
    }

    @Override
    Rank getStartRow() {
        return WHITE_PAWN_START_RANK;
    }
}
