package chess.domain.piece;

import static chess.domain.piece.PieceType.PAWN;

public class Pawn extends Piece {

    private static final Pawn WHITE = new Pawn(Color.WHITE);
    private static final Pawn BLACK = new Pawn(Color.BLACK);

    private Pawn(final Color color) {
        super(color, PAWN);
    }

    public static Pawn from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }
}
