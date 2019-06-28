package chess.domain.direction;

public class HorizonLeftKnightDirection extends Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 2;

    private HorizonLeftKnightDirection() {
        super(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
    }

    private static class HorizonLeftKnightDirectionHolder {
        private static final HorizonLeftKnightDirection instance = new HorizonLeftKnightDirection();
    }

    public static HorizonLeftKnightDirection getInstance() {
        return HorizonLeftKnightDirectionHolder.instance;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
