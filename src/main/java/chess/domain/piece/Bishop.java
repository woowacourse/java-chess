package chess.domain.piece;

import chess.domain.position.MoveChecker;
import chess.domain.position.Position;
import chess.domain.player.Team;

public class Bishop extends Piece {

    public Bishop(Position position) {
        super(State.BISHOP, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        boolean isMoveDiagonal = MoveChecker.isDiagonal(currentPosition, destinationPosition);
        if (!isMoveDiagonal) {
            throw new IllegalArgumentException("비숍은 대각선으로 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("비숍은 1칸 이상 이동해야 합니다.");
        }

        position = destinationPosition;
        return position;
    }
}
