package chess.domain.piece;

public class Bishop extends Piece {

    private static final Bishop BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Color.WHITE);

    private final boolean canMoveMoreThenOnce = true;

    private Bishop(final Color color) {
        super(color);
        initDirections();
    }

    public static Bishop of(final Color color) {
        if (color.isBlack()) {
            return BLACK_BISHOP;
        }

        return WHITE_BISHOP;
    }

    private void initDirections() {
        this.directions.add(Direction.LEFT_UP);
        this.directions.add(Direction.LEFT_DOWN);
        this.directions.add(Direction.RIGHT_UP);
        this.directions.add(Direction.RIGHT_DOWN);
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return canMoveMoreThenOnce;
    }
}
