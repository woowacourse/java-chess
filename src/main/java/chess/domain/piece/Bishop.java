package chess.domain.piece;

import static chess.domain.movement.Movement.CONTINUOUS_DIAGONAL;
import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;
import static chess.domain.piece.PieceType.BISHOP;

import chess.domain.Team;
import chess.domain.movement.Movement;

public final class Bishop extends Piece {
    public Bishop(final Team team) {
        super(team, BISHOP);
    }

    @Override
    public void canMove(final int fileInterval, final int rankInterval, final boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
    }

    private void validateMovement(final Movement movement) {
        if (movement != CONTINUOUS_DIAGONAL && movement != DISCONTINUOUS_DIAGONAL) {
            throw new IllegalArgumentException("비숍은 대각선으로만 이동할 수 있습니다.");
        }
    }
}
