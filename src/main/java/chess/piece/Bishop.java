package chess.piece;

import java.util.List;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 8;

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.POSITIVE_SLOPE_DIAGONAL,
            Direction.NEGATIVE_SLOPE_DIAGONAL
    );

    public Bishop(Color color) {
        super(DIRECTIONS, new PieceAttributes(PieceType.BISHOP, color));
    }
}
