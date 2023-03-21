package chess.domain.piece.pawn;

import static chess.domain.piece.PieceType.PAWN;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.Piece;

public class Pawn extends Piece {
    public Pawn(final Team team) {
        super(team, PAWN);
    }

    @Override
    public void validateSpecialMovement(int fileInterval, int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!PAWN.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }
}
