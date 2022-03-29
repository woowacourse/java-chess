package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;

public class Knight extends Piece {

    public Knight(Position position) {
        super(State.KNIGHT, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        if (!currentPosition.isMoveOfKnight(destinationPosition)) {
            throw new IllegalArgumentException("나이트는 상하좌우로 1칸 이동 후 대각선으로 1칸 이동해야 합니다.");
        }
        this.position = destinationPosition;
        return position;
    }
}
