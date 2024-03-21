package chess.domain.piece;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    public static Bishop of(final Color color) {
        return new Bishop(color);
    }
}
