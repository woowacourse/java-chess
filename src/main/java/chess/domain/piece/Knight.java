package chess.domain.piece;

public class Knight extends Piece {

    private static final Knight BLACK_KNIGHT = new Knight(Color.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Color.WHITE);

    private Knight(final Color color) {
        super(color);
        initDirections();
    }

    public static Knight of(final Color color) {
        if (color.isBlack()) {
            return BLACK_KNIGHT;
        }

        return WHITE_KNIGHT;
    }

    private void initDirections() {
        this.directions.add(Direction.KNIGHT_LEFT_UP);
        this.directions.add(Direction.KNIGHT_LEFT_DOWN);
        this.directions.add(Direction.KNIGHT_RIGHT_UP);
        this.directions.add(Direction.KNIGHT_RIGHT_DOWN);
        this.directions.add(Direction.KNIGHT_UP_LEFT);
        this.directions.add(Direction.KNIGHT_UP_RIGHT);
        this.directions.add(Direction.KNIGHT_DOWN_LEFT);
        this.directions.add(Direction.KNIGHT_DOWN_RIGHT);
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
