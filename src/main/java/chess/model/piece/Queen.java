package chess.model.piece;

public class Queen extends Piece {
    private static final Piece BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Piece WHITE_QUEEN = new Queen(Color.WHITE);

    private Queen(Color color) {
        super(color, Type.QUEEN);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_QUEEN;
        }
        return WHITE_QUEEN;
    }

    @Override
    public boolean isValid(Movement movement) {
        return movement.isDiagonal() || movement.isStraight();
    }
}
