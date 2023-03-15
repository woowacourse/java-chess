package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

public class RookMove extends Move implements Movable {

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = getAllPositions(source, Direction.getFourDirections(), BOARD_SIZE);
        return allPositions.contains(target);
    }
}
