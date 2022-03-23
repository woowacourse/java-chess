package chess.domain.piece;

public class Queen extends Piece {

    private static final String WHITE_SIGNATURE = "q";
    private static final String BLACK_SIGNATURE = "Q";

    private Queen(Position position, String signature) {
        super(position, signature);
    }

    public static Queen createWhite(Position position) {
        return new Queen(position, WHITE_SIGNATURE);
    }

    public static Queen createBlack(Position position) {
        return new Queen(position, BLACK_SIGNATURE);
    }

    public boolean isMovable(Piece piece) {
        return false;
    }
}
