package chess.domain.piece;

public class Bishop extends Piece {

    private static final char WHITE_SIGNATURE = 'b';
    private static final char BLACK_SIGNATURE = 'B';

    private Bishop(Position position, char signature) {
        super(position, signature);
    }

    public static Bishop createWhite(Position position) {
        return new Bishop(position, WHITE_SIGNATURE);
    }

    public static Bishop createBlack(Position position) {
        return new Bishop(position, BLACK_SIGNATURE);
    }
}
