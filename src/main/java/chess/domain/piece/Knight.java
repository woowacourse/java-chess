package chess.domain.piece;

import static chess.domain.piece.PieceType.KNIGHT;

public class Knight extends Piece {

    private static final Knight WHITE = new Knight(Color.WHITE);
    private static final Knight BLACK = new Knight(Color.BLACK);

    private Knight(final Color color) {
        super(color, KNIGHT);
    }

    public static Knight from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }
}
