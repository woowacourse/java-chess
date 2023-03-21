package chess.domain.piece.pawn;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.piece.PieceType.INITIAL_PAWN;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.Piece;

public class InitialPawn extends Piece {

    private static final int IMMOVABLE_RANK_BOUNDARY = 2;
    private static final int IMMOVABLE_FILE_BOUNDARY = 0;
    private static final int MOVABLE_RANK_BOUNDARY = 0;

    public InitialPawn(final Team team) {
        super(team, INITIAL_PAWN);
    }

    @Override
    public void validateSpecialMovement(final int fileInterval, final int rankInterval) {
        if (Math.abs(rankInterval) > IMMOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("폰은 한 칸 또는 두 칸만 이동할 수 있습니다.");
        }

        if (Math.abs(fileInterval) > IMMOVABLE_FILE_BOUNDARY) {
            throw new IllegalArgumentException("폰은 앞으로만 움직일 수 있습니다.");
        }

        if (this.getTeam() == WHITE && rankInterval < MOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("화이트 폰은 위로만 움직일 수 있습니다.");
        }

        if (this.getTeam() == BLACK && rankInterval > MOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("블랙 폰은 아래로만 움직일 수 있습니다.");
        }

        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!INITIAL_PAWN.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }
}
