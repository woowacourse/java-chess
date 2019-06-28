package chess.domain.direction;

public class VerticalDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 0;

    private VerticalDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class VerticalDirectionHolder {
        private static final VerticalDirection instance = new VerticalDirection();
    }

    public static VerticalDirection getInstance() {
        return VerticalDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
