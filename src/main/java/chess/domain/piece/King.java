package chess.domain.piece;

import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;
import static chess.domain.movement.Movement.DISCONTINUOUS_STRAIGHT;
import static chess.domain.piece.PieceType.KING;

import chess.domain.Team;
import chess.domain.movement.Movement;

public final class King extends Piece {

    public King(final Team team) {
        super(team, KING);
    }

    @Override
    public void canMove(final int fileInterval, final int rankInterval, final boolean canAttack) {
        final Movement movement = Movement.of(fileInterval, rankInterval);
        validateMovement(movement);
    }

    private void validateMovement(Movement movement) {
        if (movement != DISCONTINUOUS_DIAGONAL && movement != DISCONTINUOUS_STRAIGHT) {
            throw new IllegalArgumentException("킹은 한 칸만 이동 가능합니다.");
        }
    }
}
