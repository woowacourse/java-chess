package chess.domain.piece;

public class King extends Piece {

    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);

    private King(final Color color) {
        super(color);
        initDirections();
    }

    public static King of(final Color color) {
        if (color.isBlack()) {
            return BLACK_KING;
        }

        return WHITE_KING;
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
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
