package chess.domain.piece;

import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;

import java.util.Objects;

public abstract class Piece {
    protected final Team team;

    protected Piece(Team team) {
        this.team = team;
    }

    public String getSymbol() {
        return team.getSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(team, piece.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
