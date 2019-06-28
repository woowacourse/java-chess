package chess.domain.direction;

public class VerticalRightKnightDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 2;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;

    private VerticalRightKnightDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class VerticalRightKnightDirectionHolder {
        private static final VerticalRightKnightDirection instance = new VerticalRightKnightDirection();
    }

    public static VerticalRightKnightDirection getInstance() {
        return VerticalRightKnightDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference;
    }
}
