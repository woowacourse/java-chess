package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.INITIAL_PAWN;

import chess.domain.movement.Movement;

public class InitialPawn extends Piece {

    public InitialPawn(final Color color) {
        super(color, INITIAL_PAWN);
    }

    @Override
    public void validateMovement(int fileInterval, int rankInterval) {
        if (Math.abs(rankInterval) > 2) {
            throw new IllegalArgumentException("폰은 한 칸 또는 두 칸만 이동할 수 있습니다.");
        }

        if (Math.abs(fileInterval) > 0) {
            throw new IllegalArgumentException("폰은 앞으로만 움직일 수 있습니다.");
        }

        if (this.getColor() == WHITE && rankInterval < 0) {
            throw new IllegalArgumentException("화이트 폰은 위로만 움직일 수 있습니다.");
        }

        if (this.getColor() == BLACK && rankInterval > 0) {
            throw new IllegalArgumentException("블랙 폰은 아래로만 움직일 수 있습니다.");
        }

        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!INITIAL_PAWN.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }
}
