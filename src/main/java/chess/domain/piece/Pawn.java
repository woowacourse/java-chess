package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;

public class Pawn extends Piece {

    private static final int PAWN_DEFAULT_MOVE_DISTANCE = 1;
    private static final int PAWN_FIRST_MOVE_DISTANCE = 2;
    private static final int PAWN_DIAGONAL_MOVE_DISTANCE = 2;

    public Pawn(Position position) {
        super(State.PAWN, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        if (!currentPosition.isMoveForward(destinationPosition, team)) {
            throw new IllegalArgumentException("폰은 캡쳐할 수 있는 상대말이 없는 경우, 앞으로만 이동할 수 있습니다.");
        }
        if (!position.isFirstTurnOfPawn()) {
            validateDefaultMoveDistance(currentPosition.calculateDistance(destinationPosition));
        }
        validateFirstTurnMove(currentPosition, destinationPosition);
        this.position = destinationPosition;
        return position;
    }

    private void validateFirstTurnMove(final Position currentPosition, final Position destinationPosition) {
        final int distance = currentPosition.calculateDistance(destinationPosition);
        if (distance != PAWN_DEFAULT_MOVE_DISTANCE && distance != PAWN_FIRST_MOVE_DISTANCE) {
            throw new IllegalArgumentException("폰은 첫번째 턴에는 1칸 또는 2칸만 이동할 수 있습니다.");
        }
    }

    private void validateDefaultMoveDistance(final int distance) {
        if (distance != PAWN_DEFAULT_MOVE_DISTANCE) {
            throw new IllegalArgumentException("폰은 앞으로 1칸만 이동할 수 있습니다.");
        }
    }

    @Override
    public Position capture(final Position currentPosition, final Position destinationPosition, final Team team) {
        final boolean isMoveForwardDiagonal = currentPosition.isMoveDiagonalForward(destinationPosition, team);
        final int moveDistance = currentPosition.calculateDistance(destinationPosition);
        if (!isMoveForwardDiagonal) {
            throw new IllegalArgumentException("폰은 캡쳐 목적으로는 대각선으로만 이동할 수 있습니다.");
        }
        if (moveDistance != PAWN_DIAGONAL_MOVE_DISTANCE) {
            throw new IllegalArgumentException("폰은 대각선으로 1칸만 이동할 수 있습니다.");
        }
        this.position = destinationPosition;
        return position;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
