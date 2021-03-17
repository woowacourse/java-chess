package chess.domain.piece;

public class Blank extends Piece {
    private static final String BLANK_NOTATION = ".";

    public Blank() {
        super(BLANK_NOTATION);
    }
}
