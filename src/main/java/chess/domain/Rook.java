package chess.domain;

public class Rook extends Piece {

    private static final char WHITE_SIGNATURE = 'r';
    private static final char BLACK_SIGNATURE = 'R';

    private Rook(Position position, char signature) {
        super(position, signature);
    }

    public static Rook createWhite(Position position) {
        return new Rook(position, WHITE_SIGNATURE);
    }

    public static Rook createBlack(Position position) {
        return new Rook(position, BLACK_SIGNATURE);
    }
}
