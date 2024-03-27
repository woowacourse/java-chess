package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;

public class King extends Piece {

    private static final int MAX_UNIT_MOVE = 1;
    private static final List<Direction> KING_DIRECTION = List.of(
            Direction.POSITIVE_FILE_POSITIVE_RANK,
            Direction.POSITIVE_FILE_NEGATIVE_RANK,
            Direction.NEGATIVE_FILE_POSITIVE_RANK,
            Direction.NEGATIVE_FILE_NEGATIVE_RANK,
            Direction.POSITIVE_FILE_SAME_RANK,
            Direction.NEGATIVE_FILE_SAME_RANK,
            Direction.SAME_FILE_POSITIVE_RANK,
            Direction.SAME_FILE_NEGATIVE_RANK
    );

    public King(Color color) {
        super(color, KING_DIRECTION);
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
