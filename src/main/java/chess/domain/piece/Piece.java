package chess.domain.piece;

import chess.domain.Turn;
import chess.domain.piece.info.Team;
import chess.domain.piece.info.Trace;
import chess.domain.position.Position;

public abstract class Piece {

    protected Team team;
    protected Trace trace;

    protected Piece(final Team team) {
        this.team = team;
        this.trace = new Trace();
    }

    public abstract boolean canMove(Position startPosition, Position endPosition);

    public abstract boolean canAttack(Position startPosition, Position endPosition);

    public void addTrace(final Turn turn, final Position lastPosition) {
        trace.add(turn, lastPosition);
    }

    public Team getTeam() {
        return team;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }
}
