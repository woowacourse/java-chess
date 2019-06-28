package chess.domain.direction;

public class VerticalLeftKnightDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 2;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;

    private VerticalLeftKnightDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class VerticalLeftKnightDirectionHolder {
        private static final VerticalLeftKnightDirection instance = new VerticalLeftKnightDirection();
    }

    public static VerticalLeftKnightDirection getInstance() {
        return VerticalLeftKnightDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference * REVERSE_FACTOR;
    }
}
