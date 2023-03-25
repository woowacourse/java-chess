package chess.domain.piece.info;

import chess.domain.Turn;
import chess.domain.position.Position;

public class Log {

    private final Turn turn;
    private final Position position;

    Log(final Turn turn, final Position position) {
        this.turn = turn;
        this.position = position;
    }

    public boolean isSoonMovedTwo(final Turn turn, final Position position) {
        return isSoonMoved(turn) && isMovedTwo(position);
    }

    private boolean isSoonMoved(final Turn turn) {
        return this.turn.next().equals(turn);
    }

    private boolean isMovedTwo(final Position position) {
        return Math.abs(position.calculateRankDistance(this.position)) == 2;
    }
}
