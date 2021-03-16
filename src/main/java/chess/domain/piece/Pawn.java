package chess.domain.piece;

public class Pawn extends Piece{
    protected Pawn(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Pawn from(final String piece){
        validate(piece);
        return new Pawn(piece, isBlack(piece));
    }

    private static void validate(String piece) {

    }
}
