package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;

public class Bishop extends Piece {

    public Bishop(Position position) {
        super(State.BISHOP, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        if (!currentPosition.isMoveDiagonal(destinationPosition)) {
            throw new IllegalArgumentException("비숍은 대각선으로 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("비숍은 1칸 이상 이동해야 합니다.");
        }
        return position = destinationPosition;
    }

    @Override
    public boolean exist(final Position checkingPosition) {
        return position.equals(checkingPosition);
    }
}
