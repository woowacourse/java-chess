package chess.domain.piece;

import static chess.domain.Team.*;
import static chess.domain.piece.PieceType.INITIAL_PAWN;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class InitialPawn extends Piece {
    private static final int MOVE_LIMIT = 2;
    private static final int ZERO = 0;

    public InitialPawn(final Team team) {
        super(team, INITIAL_PAWN);
    }

    @Override
    public void validateMovement(int fileInterval, int rankInterval) {
        if (Math.abs(rankInterval) > MOVE_LIMIT) {
            throw new IllegalArgumentException("폰은 한 칸 또는 두 칸만 이동할 수 있습니다.");
        }

        if (Math.abs(fileInterval) > ZERO) {
            throw new IllegalArgumentException("폰은 앞으로만 움직일 수 있습니다.");
        }

        if (this.getColor() == WHITE && rankInterval < ZERO) {
            throw new IllegalArgumentException("화이트 폰은 위로만 움직일 수 있습니다.");
        }

        if (this.getColor() == BLACK && rankInterval > ZERO) {
            throw new IllegalArgumentException("블랙 폰은 아래로만 움직일 수 있습니다.");
        }

        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!INITIAL_PAWN.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }
}
