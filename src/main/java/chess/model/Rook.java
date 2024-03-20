package chess.model;

public class Rook extends Piece {

    public Rook(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        return distance.isCrossMovement();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "r";
        }
        return "R";
    }
}
