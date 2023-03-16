package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

public class RookMove implements Movable {
    private static final int ROOK_MAX_MOVE_COUNT = 8;

    private final Move move;

    public RookMove() {
        this.move = new Move();
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = move.getAllPositions(source, Direction.getFourDirections(), ROOK_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
