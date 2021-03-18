package chess.domain.piece.team;

import java.util.Objects;

public abstract class Team {
    protected final Symbol symbol;

    public Team(final Symbol symbol) {
        this.symbol = symbol;
    }

    public abstract String getSymbol();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return symbol == team.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
