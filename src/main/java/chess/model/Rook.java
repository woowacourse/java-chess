package chess.model;

import java.util.List;

public class Rook extends Piece {

    public Rook(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        if (distance.isCrossMovement()) {
            return distance.findPath(source);
        }
        return List.of();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "r";
        }
        return "R";
    }
}
