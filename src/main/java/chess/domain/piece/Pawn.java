package chess.domain.piece;

public class Pawn extends Piece {

    private static final char WHITE_SIGNATURE = 'p';
    private static final char BLACK_SIGNATURE = 'P';

    private Pawn(Position position, char signature) {
        super(position, signature);
    }

    public static Pawn createWhite(Position position) {
        return new Pawn(position, WHITE_SIGNATURE);
    }

    public static Pawn createBlack(Position position) {
        return new Pawn(position, BLACK_SIGNATURE);
    }
}
