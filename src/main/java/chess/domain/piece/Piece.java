package chess.domain.piece;

import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    protected final String symbol;

    protected Piece(Team team) {
        this(team, ".");
    }

    protected Piece(Team team, String symbol) {
        this.team = team;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team && Objects.equals(symbol, piece.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, symbol);
    }
}
