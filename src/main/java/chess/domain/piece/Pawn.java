package chess.domain.piece;

import static chess.domain.Team.*;
import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;
import static chess.domain.movement.Movement.DISCONTINUOUS_STRAIGHT;
import static chess.domain.piece.InitialPawn.ZERO;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.Team;
import chess.domain.movement.Movement;

public final class Pawn extends Piece {
    public Pawn(final Team team) {
        super(team, PAWN);
    }

    @Override
    public void canMove(int fileInterval, int rankInterval, boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
        validateAdvance(rankInterval);
        validateVertical(fileInterval, movement);
        validateDiagonal(movement, canAttack);
    }

    private void validateMovement(final Movement movement) {
        if (movement != DISCONTINUOUS_DIAGONAL && movement != DISCONTINUOUS_STRAIGHT) {
            throw new IllegalArgumentException("폰은 첫 이동이 아니면 조건에 따라 대각선 또는 앞으로 한 칸만 이동 가능합니다.");
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

    private void validateVertical(final int fileInterval, final Movement movement) {
        if (movement == DISCONTINUOUS_STRAIGHT && fileInterval != ZERO) {
            throw new IllegalArgumentException("폰은 좌우로 움직일 수 없습니다.");
        }
    }

    private void validateDiagonal(final Movement movement, final boolean canAttack) {
        if (movement == DISCONTINUOUS_DIAGONAL && !canAttack) {
            throw new IllegalArgumentException("폰은 첫 이동이나 대각선에 적이 위치하지 않는다면 앞으로 한 칸만 이동 가능합니다.");
        }
    }
}
