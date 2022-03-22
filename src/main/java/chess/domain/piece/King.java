package chess.domain.piece;

public class King extends Piece {

    private static final String KING_NAME = "K";

    private King(String name) {
        super(name);
    }

    public static King create() {
        return new King(KING_NAME);
    }
}
