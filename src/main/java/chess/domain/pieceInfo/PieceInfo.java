package chess.domain.pieceInfo;

import java.util.Objects;

public class PieceInfo {
    private final Position position;
    private final Team team;

    public PieceInfo(final Position position, final Team team) {
        this.position = position;
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isSameTeam(final Team otherTeam) {
        return team == otherTeam;
    }

    public PieceInfo renewPosition(final Position newPosition) {
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
