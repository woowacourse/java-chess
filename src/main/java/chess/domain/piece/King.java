package chess.domain.piece;

public class King extends Piece{
    protected King(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static King from(final String piece){
        validate(piece);
        return new King(piece, isBlack(piece));
    }

    private static void validate(String piece) {

    }
}
