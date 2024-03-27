package chess.piece;

public class InitPawn extends Pawn {

    private static final int MAX_MOVE_STEP = 2;

    public InitPawn(Color color) {
        super(color);
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_MOVE_STEP;
    }

    @Override
    public boolean isInitPawn() {
        return true;
    }
}
