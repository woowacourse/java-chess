package chess.domain.piece;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    public static Rook of(final Color color) {
        return new Rook(color);
    }
}
