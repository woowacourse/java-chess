package chess.domain.piece;

import chess.domain.point.Point;

import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private final Type type;
    private final Team team;

    public Piece(Type type, Team team) {
        this.type = type;
        this.team = team;
    }

    public abstract boolean isMovable(Point departure, Point destination, final Map<Point, Piece> board);

    public boolean hasSameTeamPieceAtDestination(final Piece pieceAtDeparture, final Piece pieceAtDestination) {
        return pieceAtDeparture.team == pieceAtDestination.team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public Type getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
