package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

public final class KingMove implements Movable {

    private final Move move;

    public KingMove() {
        this.move = new Move();
    }

    private static final int KING_MAX_MOVE_COUNT = 1;

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = move.getAllPositions(source, Direction.getAllDirections(), KING_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
