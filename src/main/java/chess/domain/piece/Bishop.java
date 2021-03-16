package chess.domain.piece;

public class Bishop extends Piece{
    protected Bishop(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Bishop from(final String piece){
        validate(piece);
        return new Bishop(piece, isBlack(piece));
    }

    private static void validate(String piece) {

    }
}
