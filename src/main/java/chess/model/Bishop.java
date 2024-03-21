package chess.model;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (distance.isDiagonalMovement()) {
            return distance.findPath(source);
        }
        return List.of();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "b";
        }
        return "B";
    }
}
