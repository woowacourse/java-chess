package chess.domain.piece;

public class Rook extends Piece{
    protected Rook(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Rook from(final String piece){
        validate(piece);
        return new Rook(piece, isBlack(piece));
    }

    private static void validate(String piece) {

    }
}
