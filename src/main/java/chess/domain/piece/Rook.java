package chess.domain.piece;

import chess.domain.position.MoveChecker;
import chess.domain.position.Position;
import chess.domain.player.Team;

public class Rook extends Piece {

    public Rook(Position position) {
        super(State.ROOK, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        boolean isMoveLinear = MoveChecker.isLinear(currentPosition, destinationPosition);
        if (!isMoveLinear) {
            throw new IllegalArgumentException("룩은 상하좌우 중 한 방향으로만 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("룩은 1칸 이상 이동해야 합니다.");
        }

        position = destinationPosition;
        return position;
    }
}
