package chess.domain.piece;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.movement.Movement.CONTINUOUS_STRAIGHT;
import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;
import static chess.domain.movement.Movement.DISCONTINUOUS_STRAIGHT;
import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static java.lang.Math.abs;

import chess.domain.Team;
import chess.domain.movement.Movement;
import java.util.List;

public final class InitialPawn extends Piece {
    private static final int MOVE_LIMIT = 2;
    public static final int ZERO = 0;

    public InitialPawn(final Team team) {
        super(team, INITIAL_PAWN);
    }

    @Override
    public void canMove(final int fileInterval, final int rankInterval, final boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
        validateDistance(rankInterval);
        validateVertical(fileInterval, movement);
        validateDiagonal(movement, canAttack);
        validateAdvance(rankInterval);
    }

    private void validateMovement(Movement movement) {
        final List<Movement> movements = List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL,
                CONTINUOUS_STRAIGHT);

        if (!movements.contains(movement)) {
            throw new IllegalArgumentException("폰이 이동할 수 없는 방향입니다.");
        }
    }

    private void validateDistance(final int rankInterval) {
        if (abs(rankInterval) > MOVE_LIMIT) {
            throw new IllegalArgumentException("초기 폰은 한 칸 또는 두 칸만 이동할 수 있습니다.");
        }
    }

    private void validateVertical(final int fileInterval, final Movement movement) {
        if (movement == DISCONTINUOUS_STRAIGHT && fileInterval != ZERO) {
            throw new IllegalArgumentException("폰은 좌우로 움직일 수 없습니다.");
        }
    }

    private void validateDiagonal(final Movement movement, final boolean canAttack) {
        if (movement == DISCONTINUOUS_DIAGONAL && !canAttack) {
            throw new IllegalArgumentException("초기 위치의 폰은 대각선에 적이 위치하지 않는다면 앞으로 한 칸 또는 두 칸 이동 가능합니다.");
        }
    }

    private void validateAdvance(final int rankInterval) {
        if (this.getColor() == WHITE && rankInterval < ZERO) {
            throw new IllegalArgumentException("화이트 폰은 위로만 움직일 수 있습니다.");
        }

        if (this.getColor() == BLACK && rankInterval > ZERO) {
            throw new IllegalArgumentException("블랙 폰은 아래로만 움직일 수 있습니다.");
        }
    }



}
