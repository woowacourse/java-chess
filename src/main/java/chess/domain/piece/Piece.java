package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;
import chess.domain.piece.info.Trace;

public abstract class Piece {

    protected Team team;
    protected Trace trace;

    protected Piece(final Team team) {
        this.team = team;
        this.trace = new Trace();
    }

    abstract boolean canMove(Position startPosition, Position endPosition);

    abstract boolean canAttack(Position startPosition, Position endPosition);

    public void addTrace(final int turn, final Position lastPosition) {
        trace.add(turn, lastPosition);
    }
}
