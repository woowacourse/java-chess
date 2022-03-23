package chess.domain.piece;

public class King extends Piece {

    private static final String WHITE_SIGNATURE = "k";
    private static final String BLACK_SIGNATURE = "K";

    private King(Position position, String signature) {
        super(position, signature);
    }

    public static King createWhite(Position position) {
        return new King(position, WHITE_SIGNATURE);
    }

    public static King createBlack(Position position) {
        return new King(position, BLACK_SIGNATURE);
    }

    public boolean move(Piece piece) {
        return false;
    }
}
