package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

public class KnightMove implements Movable {
    private static final int KNIGHT_MAX_MOVE_COUNT = 1;

    private final Move move;

    public KnightMove() {
        this.move = new Move();
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = move.getAllPositions(source, Direction.getLShapeDirections(), KNIGHT_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
