package chess.domain.piece;

import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;

import chess.domain.board.Direction;
import chess.domain.board.Rank;
import java.util.List;

public final class BlackPawn extends Pawn {

    private static final List<Direction> DIRECTIONS = List.of(SOUTH, SOUTH_EAST, SOUTH_WEST);
    private static final Rank BLACK_PAWN_START_RANK = Rank.RANK_7;

    BlackPawn(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    Rank getStartRow() {
        return BLACK_PAWN_START_RANK;
    }
}
