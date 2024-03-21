package chess.piece;

import chess.board.Direction;
import java.util.List;

public class King extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public King(Color color) {
        super(PieceType.KING,
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
