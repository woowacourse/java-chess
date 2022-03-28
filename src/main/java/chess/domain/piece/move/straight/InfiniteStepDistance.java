package chess.domain.piece.move.straight;

public class InfiniteStepDistance implements Distance {

    private static class InfiniteStepDistanceHelper {
        private static final InfiniteStepDistance INSTANCE = new InfiniteStepDistance();
    }

    public static Distance getInstance() {
        return InfiniteStepDistanceHelper.INSTANCE;
    }

    @Override
    public int getDistance() {
        return Integer.MAX_VALUE;
    }
}
