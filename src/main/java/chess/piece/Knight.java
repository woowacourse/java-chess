package chess.piece;

import chess.board.Position;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final double slope = position.getSlope(targetPosition);

        return slope == 0.5 || slope == 2.0;
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        return Collections.emptyList();
    }
}
