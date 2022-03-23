package chess.domain.piece;

public class King extends Piece {

    private static final String NAME = "k";

    public King(final Color color) {
        super(color, NAME);
    }
}
