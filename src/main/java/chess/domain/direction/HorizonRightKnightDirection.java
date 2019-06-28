package chess.domain.direction;

public class HorizonRightKnightDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 2;

    private HorizonRightKnightDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class HorizonRightKnightDirectionHolder {
        private static final HorizonRightKnightDirection instance = new HorizonRightKnightDirection();
    }

    public static HorizonRightKnightDirection getInstance() {
        return HorizonRightKnightDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference * REVERSE_FACTOR;
    }
}
