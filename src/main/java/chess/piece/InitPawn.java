package chess.piece;

public class InitPawn extends Pawn {

    private static final int MAX_UNIT_MOVE = 2;

    public InitPawn(Color color) {
        super(color);
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_UNIT_MOVE;
    }

    @Override
    public boolean isInitPawn() {
        return true;
    }
}
