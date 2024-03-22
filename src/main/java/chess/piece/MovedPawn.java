package chess.piece;

public class MovedPawn extends Pawn {

    private static final int MAX_UNIT_MOVE = 1;

    public MovedPawn(Color color) {
        super(color, MAX_UNIT_MOVE);
    }
}
