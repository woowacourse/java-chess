package chess.domain.piece;

public class Queen extends Piece {

    private static final String NAME = "q";

    public Queen(final Color color) {
        super(color, NAME);
    }
}
