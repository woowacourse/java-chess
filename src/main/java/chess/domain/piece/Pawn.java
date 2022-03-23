package chess.domain.piece;

public final class Pawn extends Piece {

    private static final String PAWN_NAME = "P";

    public Pawn(Color color) {
        super(color, PAWN_NAME, null);
    }

    @Override
    public boolean isSingleMovable() {
        return true;
    }
}
