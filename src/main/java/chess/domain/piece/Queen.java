package chess.domain.piece;

public class Queen extends Piece {

    private static final Queen BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Color.WHITE);

    private Queen(final Color color) {
        super(color);
        initDirections();
    }

    public static Queen of(final Color color) {
        if (color.isBlack()) {
            return BLACK_QUEEN;
        }

        return WHITE_QUEEN;
    }

    private void initDirections() {
        this.directions.add(Direction.LEFT);
        this.directions.add(Direction.RIGHT);
        this.directions.add(Direction.UP);
        this.directions.add(Direction.DOWN);
        this.directions.add(Direction.LEFT_UP);
        this.directions.add(Direction.LEFT_DOWN);
        this.directions.add(Direction.RIGHT_UP);
        this.directions.add(Direction.RIGHT_DOWN);
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
