package chess.domain.piece;

import chess.domain.player.Team;
import chess.domain.position.MoveChecker;
import chess.domain.position.Position;

public class Queen extends Piece {

    public Queen(Position position) {
        super(State.QUEEN, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        final boolean isMoveLinear = MoveChecker.isLinear(currentPosition, destinationPosition);
        final boolean isMoveDiagonal = MoveChecker.isDiagonal(currentPosition, destinationPosition);
        if (!isMoveLinear && !isMoveDiagonal) {
            throw new IllegalArgumentException("퀸은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
        }

        position = destinationPosition;
        return position;
    }
}
