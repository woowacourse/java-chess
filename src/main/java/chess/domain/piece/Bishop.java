package chess.domain.piece;

import chess.domain.piece.move.Direction;
import chess.domain.piece.move.Location;
import chess.domain.piece.move.Position;

import static chess.domain.piece.Move.MAX_MOVE_COUNT;

public class Bishop implements Movable {

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Location allPositions = Move.getLocation(source, Direction.getDiagonalDirections(), MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return canMove(source, target);
    }
}
