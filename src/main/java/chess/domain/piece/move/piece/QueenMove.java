package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import chess.domain.piece.move.Direction;
import chess.domain.piece.move.Location;

import static chess.domain.piece.move.piece.Move.MAX_MOVE_COUNT;

public final class QueenMove implements MoveRule {
    @Override
    public boolean canMove(final Position source, final Position target) {
        final Location allPositions = Move.getLocation(source, Direction.getAllDirections(), MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return canMove(source, target);
    }
}
