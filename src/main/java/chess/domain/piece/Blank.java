package chess.domain.piece;

public class Blank extends Piece {
    private static final String BLANK_PIECE_WORD = ".";

    public Blank() {
        super(Color.NONE, BLANK_PIECE_WORD);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
