package chess.domain.direction;

public class HorizonDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 0;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;

    private HorizonDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class HorizonDirectionHolder {
        private static final HorizonDirection instance = new HorizonDirection();
    }

    public static HorizonDirection getInstance() {
        return HorizonDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference;
    }
}
