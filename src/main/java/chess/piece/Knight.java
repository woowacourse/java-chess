package chess.piece;

import java.util.List;

public class Knight extends Piece {

    private static final int MAX_UNIT_MOVE = 1;
    private static final List<Direction> DIRECTIONS = List.of(
            Direction.KNIGHT
    );

    public Knight(Color color) {
        super(DIRECTIONS, new PieceAttributes(PieceType.KNIGHT, color));
    }
}
