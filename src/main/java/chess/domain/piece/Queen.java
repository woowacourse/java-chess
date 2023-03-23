package chess.domain.piece;

import static chess.domain.movement.Movement.CONTINUOUS_DIAGONAL;
import static chess.domain.movement.Movement.CONTINUOUS_STRAIGHT;
import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;
import static chess.domain.movement.Movement.DISCONTINUOUS_STRAIGHT;
import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.Team;
import chess.domain.movement.Movement;
import java.util.List;

public final class Queen extends Piece {
    public Queen(final Team team) {
        super(team, QUEEN);
    }

    @Override
    public void canMove(final int fileInterval, final int rankInterval, final boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
    }

    private void validateMovement(final Movement movement) {
        final List<Movement> movements = List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL,
                CONTINUOUS_STRAIGHT, CONTINUOUS_DIAGONAL);

        if (!movements.contains(movement)) {
            throw new IllegalArgumentException("퀸은 45도 각도의 대각선 또는 상하좌우로 이동 가능합니다.");
        }
    }
}
