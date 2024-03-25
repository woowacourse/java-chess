package chess.domain.piece;

public class Pawn extends Piece {

    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);

    private Pawn(final Color color) {
        super(color);
        initDirections();
    }

    public static Pawn of(final Color color) {
        if (color.isBlack()) {
            return BLACK_PAWN;
        }

        return WHITE_PAWN;
    }

    private void initDirections() {
        if (color == Color.BLACK) {
            this.directions.add(Direction.DOWN);
        }
        if (color == Color.WHITE) {
            this.directions.add(Direction.UP);
        }
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
