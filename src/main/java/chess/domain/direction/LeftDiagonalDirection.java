package chess.domain.direction;

public class LeftDiagonalDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;

    private LeftDiagonalDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class LeftDiagonalDirectionHolder {
        private static final LeftDiagonalDirection instance = new LeftDiagonalDirection();
    }

    public static LeftDiagonalDirection getInstance() {
        return LeftDiagonalDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
