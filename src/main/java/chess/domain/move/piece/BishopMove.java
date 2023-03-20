package chess.domain.move.piece;

import chess.domain.move.Direction;
import chess.domain.move.Location;
import chess.domain.piece.Position;

import static chess.domain.move.piece.Move.MAX_MOVE_COUNT;

public class BishopMove implements MoveRule {

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Location allPositions = Move.getLocation(source, Direction.getDiagonalDirections(), MAX_MOVE_COUNT);
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
