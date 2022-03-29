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
        this.position = destinationPosition;
        return position;
    }
}
