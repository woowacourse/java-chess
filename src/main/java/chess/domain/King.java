package chess.domain;

public class King extends Piece {

    private static final char WHITE_SIGNATURE = 'k';
    private static final char BLACK_SIGNATURE = 'K';

    private King(Position position, char signature) {
        super(position, signature);
    }

    public static King createWhite(Position position) {
        return new King(position, WHITE_SIGNATURE);
    }

    public static King createBlack(Position position) {
        return new King(position, BLACK_SIGNATURE);
    }
}
