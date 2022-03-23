package chess.domain.piece;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";
    private static final int KNIGHT_MOVABLE_DISTANCE = 3;

    public Knight(Color color) {
        super(color, KNIGHT_NAME);
    }
}
