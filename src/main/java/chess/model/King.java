package chess.model;

import java.util.List;

public class King extends Piece {
    private static final int DISPLACEMENT = 1;

    public King(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        if (distance.hasSame(DISPLACEMENT)) {
            return List.of(target);
        }
        return List.of();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "k";
        }
        return "K";
    }
}
