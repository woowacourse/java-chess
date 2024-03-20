package chess.model;

public class King extends Piece {
    private static final int DISPLACEMENT = 1;

    public King(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public boolean canMove(ChessPosition target) {
        Distance distance = target.calculateDistance(this.chessPosition);
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
