package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.Objects;

public abstract class Piece {
    private final Position position;
    private final Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract Kind findKind();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, team);
    }
}
