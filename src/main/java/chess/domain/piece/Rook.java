package chess.domain.piece;

public class Rook extends Piece {

    private static final Rook WHITE = new Rook(Color.WHITE);
    private static final Rook BLACK = new Rook(Color.BLACK);

    private Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    public static Rook from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }
}
