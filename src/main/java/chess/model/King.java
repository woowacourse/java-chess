package chess.model;

public class King extends Piece {
    private static final int DISPLACEMENT = 1;

    public King(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        return distance.hasSame(DISPLACEMENT);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "k";
        }
        return "K";
    }
}
