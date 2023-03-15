package chess.domain.move;

import chess.domain.piece.Position;

import java.util.Set;

public final class QueenMove extends Move {

    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = getAllPositions(source, Direction.getAllDirections(), BOARD_SIZE);
        return allPositions.contains(target);
    }
}
