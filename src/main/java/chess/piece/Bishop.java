package chess.piece;

import chess.board.Direction;
import java.util.List;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Bishop(Color color) {
        super(PieceType.BISHOP,
                color,
                List.of(
                        Direction.POSITIVE_FILE_POSITIVE_RANK,
                        Direction.POSITIVE_FILE_NEGATIVE_RANK,
                        Direction.NEGATIVE_FILE_POSITIVE_RANK,
                        Direction.NEGATIVE_FILE_NEGATIVE_RANK
                )
        );
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
