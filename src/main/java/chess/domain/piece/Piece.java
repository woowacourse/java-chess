package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import java.util.Objects;

public abstract class Piece {

    private final Team team;
    private final String name;
    private final double point;

    protected Piece(final Team team, final String name, final double point) {
        this.team = team;
        this.name = name;
        this.point = point;
    }

    public final boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public final Team getTeam() {
        return team;
    }

    public final double getPoint() {
        return point;
    }

    public final String getName() {
        return name;
    }

    public final String convertPieceToString() {
        if (this.isBlank()) {
            return "blank";
        }
        return String.format("%s_%s", team.getValue(), getName());
    }

    public abstract boolean isBlank();

    public abstract MoveStrategy getMoveStrategy();

    public abstract boolean isKing();

    public abstract boolean isPawn();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
