package chess.piece;

import java.util.List;

public class Rook extends Piece {

    private static final int MAX_UNIT_MOVE = 1;
    private static final List<Direction> DIRECTIONS = List.of(
            Direction.HORIZONTAL,
            Direction.VERTICAL
    );

    public Rook(Color color) {
        super(DIRECTIONS, new PieceAttributes(PieceType.ROOK, color));
    }
}
