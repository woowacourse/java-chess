package chess.model.piece;

import chess.model.Position;
import chess.model.Team;
import chess.model.Turn;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected Team team;

    protected Piece(Team team) {
        this.team = team;
    }

    public final boolean isSameTeam(Piece targetPiece) {
        return team.equals(targetPiece.team);
    }

    public final boolean isOtherTeam(Piece targetPiece) {
        return team.getForwardDirection() + targetPiece.team.getForwardDirection() == 0;
    }

    public final boolean isTeam(Team team) {
        return this.team == team;
    }

    public final boolean isCurrentTurn(Turn turn) {
        return turn.isCurrentTeam(team);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKill(Position source, Position target, Piece targetPiece) {
        return false;
    }

    public abstract double getScore();

    public abstract List<Position> getIntervalPosition(Position source, Position target);

    public boolean isMovable(Position source, Position target) {
        return false;
    } // abstract로 변경 필요

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
