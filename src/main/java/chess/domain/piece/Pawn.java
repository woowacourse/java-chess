package chess.domain.piece;

public class Pawn extends Piece {

    private static final String NAME = "p";

    public Pawn(final Color color) {
        super(color, NAME);
    }
}
