package chess.piece;

import java.util.List;

public class King extends Piece {

    private static final int MAX_UNIT_MOVE = 1;
    private static final List<Direction> DIRECTIONS = List.of(
            Direction.HORIZONTAL,
            Direction.VERTICAL,
            Direction.POSITIVE_SLOPE_DIAGONAL,
            Direction.NEGATIVE_SLOPE_DIAGONAL
    );

    public King(Color color) {
        super(DIRECTIONS, new PieceAttributes(PieceType.KING, color));
    }
}
