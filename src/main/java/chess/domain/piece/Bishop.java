package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 7;
    private static final List<Direction> BISHOP_DIRECTION = List.of(
            Direction.POSITIVE_FILE_POSITIVE_RANK,
            Direction.POSITIVE_FILE_NEGATIVE_RANK,
            Direction.NEGATIVE_FILE_POSITIVE_RANK,
            Direction.NEGATIVE_FILE_NEGATIVE_RANK
    );

    public Bishop(Color color) {
        super(color, BISHOP_DIRECTION);
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
