package chess.domain.piece;

import chess.domain.player.Team;
import chess.domain.position.MoveChecker;
import chess.domain.position.Position;
import java.util.Set;

public class Pawn extends Piece {

    private static final int DEFAULT_MOVE_DISTANCE = 1;
    private static final int DIAGONAL_MOVE_DISTANCE = 2;
    private static final Set<Integer> FIRST_MOVE_DISTANCE = Set.of(1, 2);

    public Pawn(Position position) {
        super(State.PAWN, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        boolean isMoveForward = MoveChecker.isForward(currentPosition, destinationPosition, team);
        if (!isMoveForward) {
            throw new IllegalArgumentException("이동 방향이 앞이 아닙니다.");
        }
        if (position.isFirstTurnOfPawn()) {
            return moveFirstTurn(currentPosition, destinationPosition);
        }
        return defaultMove(currentPosition, destinationPosition);
    }

    private Position moveFirstTurn(Position currentPosition, Position destinationPosition) {
        validateFirstTurnMove(currentPosition.calculateDistance(destinationPosition));
        position = destinationPosition;
        return position;
    }

    private Position defaultMove(Position currentPosition, Position destinationPosition) {
        validateDefaultMoveDistance(currentPosition.calculateDistance(destinationPosition));
        position = destinationPosition;
        return position;
    }

    private void validateFirstTurnMove(final int distance) {
        final boolean isMovable = FIRST_MOVE_DISTANCE.contains(distance);
        if (!isMovable) {
            throw new IllegalArgumentException("폰은 첫번째 턴에는 1칸 또는 2칸만 이동할 수 있습니다.");
        }
    }

    private void validateDefaultMoveDistance(final int distance) {
        if (distance != DEFAULT_MOVE_DISTANCE) {
            throw new IllegalArgumentException("폰은 앞으로 1칸만 이동할 수 있습니다.");
        }
    }

    @Override
    public Position capture(final Position currentPosition, final Position destinationPosition, final Team team) {
        final boolean isMoveForwardDiagonal = MoveChecker.isDiagonalForward(currentPosition, destinationPosition,
                team);
        final int moveDistance = currentPosition.calculateDistance(destinationPosition);
        if (!isMoveForwardDiagonal) {
            throw new IllegalArgumentException("이동 방향이 대각선이 아닙니다.");
        }
        if (moveDistance != DIAGONAL_MOVE_DISTANCE) {
            throw new IllegalArgumentException("대각선으로 이동하는 거리가 1칸이 아닙니다.");
        }
        return position = destinationPosition;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
