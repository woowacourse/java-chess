package chess.domain;

import java.util.Objects;

class Piece {

    private final PieceType type;
    private final Team team;

    public Piece(PieceType type, Team team) {
        this.type = type;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type == piece.type && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, team);
    }
}
