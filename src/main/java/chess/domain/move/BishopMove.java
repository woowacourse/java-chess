package chess.domain.move;

import chess.domain.piece.Position;

import static chess.domain.move.Move.MAX_MOVE_COUNT;

public class BishopMove implements Movable {
    
    @Override
    public boolean canMove(final Position source, final Position target) {
        final Location allPositions = Move.getAllPositions(source, Direction.getDiagonalDirections(), MAX_MOVE_COUNT);
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
