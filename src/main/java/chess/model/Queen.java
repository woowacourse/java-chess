package chess.model;

import java.util.List;

public class Queen extends Piece {

    public Queen(final Side side) {
        super(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "q";
        }
        return "Q";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (canMove(distance)) {
            return distance.findPath(source);
        }
        return List.of();
    }

    private boolean canMove(Distance distance) {
        return distance.isCrossMovement() || distance.isDiagonalMovement();
    }
}
