package chess.domain.piece;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    public static Knight of(final Color color) {
        return new Knight(color);
    }
}
