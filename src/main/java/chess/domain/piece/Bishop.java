package chess.domain.piece;

public class Bishop extends Piece {

    private static final String WHITE_SIGNATURE = "b";
    private static final String BLACK_SIGNATURE = "B";

    private Bishop(Position position, String signature) {
        super(position, signature);
    }

    public static Bishop createWhite(Position position) {
        return new Bishop(position, WHITE_SIGNATURE);
    }

    public static Bishop createBlack(Position position) {
        return new Bishop(position, BLACK_SIGNATURE);
    }

    public boolean move(Piece piece) {
        return false;
    }
}
