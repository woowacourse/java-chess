package chess.domain;

import java.util.Objects;

public class PieceInfo {
    private final Position position;
    private final Team team;

    public PieceInfo(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isSameTeam(Team otherTeam) {
        return team == otherTeam;
    }

    public PieceInfo renewPosition(Position newPosition) {
        return new PieceInfo(newPosition, team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceInfo pieceInfo = (PieceInfo) o;
        return Objects.equals(position, pieceInfo.position) && team == pieceInfo.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, team);
    }
}
