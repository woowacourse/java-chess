package chess.domain.piece;

public class Blank extends Piece {
    private static final String BLANK_PIECE_WORD = ".";

    public Blank(Color color) {
        super(color, BLANK_PIECE_WORD);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
