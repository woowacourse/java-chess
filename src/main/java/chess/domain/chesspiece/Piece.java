package chess.domain.chesspiece;

import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract List<Position> getMovingRoute(Position source, Position target);
    public abstract List<Position> getAttackRoute(Position source, Position target);

    protected abstract void validateMovingRule(Position source, Position target);

    public abstract Role getRole();

    public abstract boolean isPawn();

    public abstract boolean isEmpty();

    public final Team getTeam() {
        return team;
    }

    public final boolean isTeam(Piece piece) {
        return team == piece.team;
    }
}
