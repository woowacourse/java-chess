package chess.domain.piece;

public class Knight extends Piece{
    protected Knight(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Knight from(final String piece){
        validate(piece);
        return new Knight(piece, isBlack(piece));
    }

    private static void validate(String piece) {

    }
}
