package chess.domain.piece;

public class King extends Piece{
    public King(Color color) {
        super(color);
    }

    public static King of(final Color color) {
        return new King(color);
    }
}
