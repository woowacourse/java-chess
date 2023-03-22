package chess.domain.piece;

import chess.domain.piece.move.Direction;
import chess.domain.piece.move.Location;
import chess.domain.piece.move.Position;

import java.util.List;

import static chess.domain.piece.Move.PAWN_MAX_MOVE_COUNT;

public class Pawn implements Movable {
    private static final int ATTACK_INDEX = 1;

    @Override
    public boolean canMove(final Position source, final Position target) {
        if (target.isRankGreaterThan(source)) {
            final Location allPositions = Move.getLocation(source,
                    List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT), PAWN_MAX_MOVE_COUNT);
            return allPositions.contains(target);
        }
        final Location allPositions = Move.getLocation(source,
                List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT), PAWN_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return Math.abs(target.calculateRankGap(source)) == ATTACK_INDEX
                && Math.abs(target.calculateFileGap(source)) == ATTACK_INDEX;
    }
}
