package chess.domain.piece;

public class Knight extends Piece {

    private static final char WHITE_SIGNATURE = 'n';
    private static final char BLACK_SIGNATURE = 'N';

    private Knight(Position position, char signature) {
        super(position, signature);
    }

    public static Knight createWhite(Position position) {
        return new Knight(position, WHITE_SIGNATURE);
    }

    public static Knight createBlack(Position position) {
        return new Knight(position, BLACK_SIGNATURE);
    }
}
