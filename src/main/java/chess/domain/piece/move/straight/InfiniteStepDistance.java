package chess.domain.piece.move.straight;

public class InfiniteStepDistance implements Distance {

    private static final Distance INIT = new InfiniteStepDistance(Integer.MAX_VALUE);

    private final int amount;

    private InfiniteStepDistance(int amount) {
        this.amount = amount;
    }

    public static Distance init() {
        return INIT;
    }

    @Override
    public Distance decreaseOne() {
        return new InfiniteStepDistance(this.amount - 1);
    }

    @Override
    public boolean isLeft() {
        return this.amount > 0;
    }
}
