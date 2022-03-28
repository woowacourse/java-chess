package chess.domain.piece.move.straight;

public class OneStepDistance implements Distance {

    private static final Distance INIT = new OneStepDistance(1);
    private final int amount;

    private OneStepDistance(int amount) {
        this.amount = amount;
    }

    public static Distance init() {
        return INIT;
    }

    @Override
    public Distance decrease() {
        return new OneStepDistance(this.amount - 1);
    }

    @Override
    public boolean isLeft() {
        return this.amount > 0;
    }
}
