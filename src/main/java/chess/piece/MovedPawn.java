package chess.piece;

public class MovedPawn extends Pawn {

    private static final int MAX_UNIT_MOVE = 1;

    public MovedPawn(Color color) {
        super(color);
    }

    @Override
    protected boolean isReachable(int distance) {
        return distance <= MAX_UNIT_MOVE;
    }
}
