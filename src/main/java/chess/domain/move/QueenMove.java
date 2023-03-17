package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

import static chess.domain.move.Move.MAX_MOVE_COUNT;

public final class QueenMove implements Movable {

    private static final Move move = new Move();

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = move.getAllPositions(source, Direction.getAllDirections(), MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return canMove(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        if (!isPossible) {
            return false;
        }
        return canMove(source, target);
    }
}
