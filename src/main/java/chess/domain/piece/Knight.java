package chess.domain.piece;

import static chess.domain.movement.Movement.CONTINUOUS_L_SHAPE;
import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.Team;
import chess.domain.movement.Movement;

public final class Knight extends Piece {
    public Knight(final Team team) {
        super(team, KNIGHT);
    }

    @Override
    public void canMove(final int fileInterval, final int rankInterval, final boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
    }

    private void validateMovement(final Movement movement) {
        if (CONTINUOUS_L_SHAPE != movement) {
            throw new IllegalArgumentException("나이트가 이동할 수 없는 방향입니다.");
        }
    }
}
