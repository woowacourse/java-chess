package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public Knight(Color color) {
        super(color, List.of());
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isOnKnightRoute(destination);
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
