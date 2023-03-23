package chess.domain.piece.pawn;

import static chess.domain.movement.Movement.CONTINUOUS_STRAIGHT;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class InitialPawn extends PawnPiece {

    private static final int IMMOVABLE_RANK_BOUNDARY = 2;

    public InitialPawn(final Team team) {
        super(team, PAWN);
    }

    @Override
    public boolean validateInitialMovement(final int fileInterval, final int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (CONTINUOUS_STRAIGHT.equals(movement) && Math.abs(rankInterval) > IMMOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("초기 폰은 최대 2칸까지 이동할 수 있습니다.");
        }
        return true;
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
