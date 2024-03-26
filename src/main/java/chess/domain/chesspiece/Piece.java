package chess.domain.chesspiece;

import chess.domain.position.Position;

import java.util.List;

import static chess.domain.chesspiece.Role.BLACK_PAWN;
import static chess.domain.chesspiece.Role.WHITE_PAWN;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract List<Position> getRoute(Position source, Position target);

    protected abstract void validateMovingRule(Position source, Position target);

    public abstract Role getRole();

    public final Team getTeam() {
        return team;
    }

    public final boolean isTeam(Piece piece) {
        return team == piece.team;
    }

    public abstract boolean isPawn();

    public abstract boolean isEmpty();
}
