package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;

public class Queen extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Queen(Color color) {
        super(PieceType.QUEEN,
                color,
                List.of(
                        Direction.POSITIVE_FILE_POSITIVE_RANK,
                        Direction.POSITIVE_FILE_NEGATIVE_RANK,
                        Direction.NEGATIVE_FILE_POSITIVE_RANK,
                        Direction.NEGATIVE_FILE_NEGATIVE_RANK,
                        Direction.POSITIVE_FILE_SAME_RANK,
                        Direction.NEGATIVE_FILE_SAME_RANK,
                        Direction.SAME_FILE_POSITIVE_RANK,
                        Direction.SAME_FILE_NEGATIVE_RANK
                )
        );
    }

    @Override
    protected int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
