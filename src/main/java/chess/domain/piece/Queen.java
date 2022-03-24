package chess.domain.piece;

public final class Queen extends Piece {

    private static final String QUEEN_NAME = "Q";

    public Queen(Color color) {
        super(color, QUEEN_NAME);
    }
}
