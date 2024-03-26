package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;

public class Rook extends Piece {

    private static final int MAX_UNIT_MOVE = 7;
    private static final List<Direction> ROOK_DIRECTION = List.of(
            Direction.POSITIVE_FILE_SAME_RANK,
            Direction.NEGATIVE_FILE_SAME_RANK,
            Direction.SAME_FILE_POSITIVE_RANK,
            Direction.SAME_FILE_NEGATIVE_RANK
    );

    public Rook(Color color) {
        super(color, ROOK_DIRECTION);
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
