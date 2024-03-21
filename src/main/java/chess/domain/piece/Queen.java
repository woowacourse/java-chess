package chess.domain.piece;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    public static Queen of(final Color color) {
        return new Queen(color);
    }
}
