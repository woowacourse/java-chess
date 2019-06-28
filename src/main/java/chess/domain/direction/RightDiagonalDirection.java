package chess.domain.direction;

public class RightDiagonalDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;

    private RightDiagonalDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class RithtDiagonalDirectionHolder {
        private static final RightDiagonalDirection instance = new RightDiagonalDirection();
    }

    public static RightDiagonalDirection getInstance() {
        return RithtDiagonalDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
