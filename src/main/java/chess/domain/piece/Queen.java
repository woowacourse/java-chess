package chess.domain.piece;

public class Queen extends Piece{
    protected Queen(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Queen from(final String piece){
        validate(piece);
        return new Queen(piece, isBlack(piece));
    }

    private static void validate(String piece) {

    }
}
