package chess.domain.piece;

import chess.domain.Turn;
import chess.domain.piece.info.Team;
import chess.domain.piece.info.Trace;
import chess.domain.position.Position;

public abstract class Piece {

    protected final Team team;
    protected final Trace trace;

    protected Piece(final Team team) {
        this.team = team;
        this.trace = new Trace();
    }

    public abstract boolean canMove(Position source, Position destination);

    public abstract boolean canAttack(Position source, Position destination);

    public void addTrace(final Turn turn, final Position lastPosition) {
        trace.add(turn, lastPosition);
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public Team getTeam() {
        return team;
    }
}
