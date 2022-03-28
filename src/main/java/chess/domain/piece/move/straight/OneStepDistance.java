package chess.domain.piece.move.straight;

public class OneStepDistance implements Distance {

    private static class OneStepDistanceHelper {
        private static final OneStepDistance INSTANCE = new OneStepDistance();
    }

    public static Distance getInstance() {
        return OneStepDistanceHelper.INSTANCE;
    }

    @Override
    public int getDistance() {
        return 1;
    }
}
