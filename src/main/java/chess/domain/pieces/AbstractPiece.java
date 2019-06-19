package chess.domain.pieces;

import chess.domain.ChessTeam;

import java.util.Objects;

public abstract class AbstractPiece implements Piece {
    protected final String name;
    protected final ChessTeam team;

    protected AbstractPiece(String name, ChessTeam team) {
        this.name = name;
        this.team = team;
    }

    @Override
    public boolean isAlly(Piece piece) {
        if (piece == null) throw new IllegalArgumentException();
        AbstractPiece that = (AbstractPiece) piece;
        return this.team == that.team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPiece that = (AbstractPiece) o;
        return name.equals(that.name) &&
                team == that.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team);
    }

    @Override
    public String toString() {
        return name;
    }
}
