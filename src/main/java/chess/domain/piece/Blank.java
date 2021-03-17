package chess.domain.piece;

public class Blank extends Piece {
    private static final String INITIAL_NAME = ".";

    public Blank() {
        super(false, INITIAL_NAME);
    }
}
