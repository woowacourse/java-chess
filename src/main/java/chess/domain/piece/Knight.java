package chess.domain.piece;

public class Knight extends Piece {

    private static final String WHITE_SIGNATURE = "n";
    private static final String BLACK_SIGNATURE = "N";

    private Knight(Position position, String signature) {
        super(position, signature);
    }

    public static Knight createWhite(Position position) {
        return new Knight(position, WHITE_SIGNATURE);
    }

    public static Knight createBlack(Position position) {
        return new Knight(position, BLACK_SIGNATURE);
    }
}
