package chess.piece;

public class MovedPawn extends Pawn {

    private static final int MAX_MOVE_STEP = 1;

    public MovedPawn(Color color) {
        super(color);
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_MOVE_STEP;
    }
}
