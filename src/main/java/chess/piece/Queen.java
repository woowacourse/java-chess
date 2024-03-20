package chess.piece;

import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.HORIZONTAL,
            Direction.VERTICAL,
            Direction.POSITIVE_SLOPE_DIAGONAL,
            Direction.NEGATIVE_SLOPE_DIAGONAL
    );

    public Queen(Color color) {
        super(DIRECTIONS, new PieceAttributes(PieceType.QUEEN, color));
    }
}
