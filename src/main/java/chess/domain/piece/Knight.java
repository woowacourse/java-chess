package chess.domain.piece;

public class Knight extends RealPiece {
    private static final String KNIGHT_WORD = "N";

    public Knight(Color color) {
        super(color, KNIGHT_WORD);
    }
}
