package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

public class BishopMove extends Move implements Movable {

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = getAllPositions(source, Direction.getDiagonalDirections(), BOARD_SIZE);
        return allPositions.contains(target);
    }
}
