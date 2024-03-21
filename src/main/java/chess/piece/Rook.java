package chess.piece;

import chess.position.Direction;
import java.util.List;

public class Rook extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Rook(Color color) {
        super(PieceType.ROOK,
                color,
                List.of(
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
