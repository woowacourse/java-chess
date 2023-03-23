package chess.domain.piece;

import static chess.domain.movement.Movement.CONTINUOUS_STRAIGHT;
import static chess.domain.movement.Movement.DISCONTINUOUS_STRAIGHT;
import static chess.domain.piece.PieceType.ROOK;

import chess.domain.Team;
import chess.domain.movement.Movement;

public final class Rook extends Piece {
    public Rook(final Team team) {
        super(team, ROOK);
    }

    @Override
    public void canMove(final int fileInterval, final int rankInterval, final boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
    }

    private void validateMovement(final Movement movement) {
        if (movement != DISCONTINUOUS_STRAIGHT && movement != CONTINUOUS_STRAIGHT) {
            throw new IllegalArgumentException("룩은 상하좌우로만 이동할 수 있습니다.");
        }
    }
}
