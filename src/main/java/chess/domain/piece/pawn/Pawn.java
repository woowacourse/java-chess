package chess.domain.piece.pawn;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.PieceType;

import static chess.domain.movement.Movement.CONTINUOUS_STRAIGHT;

public class Pawn extends PawnPiece{
    public Pawn(final Team team) {
        super(team, PieceType.PAWN);
    }

    @Override
    public boolean validateInitialMovement(final int fileInterval, final int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (CONTINUOUS_STRAIGHT.equals(movement)) {
            throw new IllegalArgumentException("초기 폰이 아닌 경우, 폰은 최대 1칸까지 앞으로 이동할 수 있습니다.");
        }
        return true;
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
