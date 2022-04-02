package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.List;
import java.util.Locale;

public abstract class DefaultPiece implements Piece {
    protected final Name name;
    protected final Team team;

    public DefaultPiece(Name name, Team team) {
        this.name = name;
        this.team = team;
    }

    public abstract boolean canMove(Piece targetAbstractPiece, Position from, Position to);

    public boolean isSameTeam(Piece targetPiece) {
        return this.team == targetPiece.getTeam();
    }

    public boolean isSameTeamOrEmpty(Team team) {
        return (team != Team.WHITE || this.team != Team.BLACK) &&
                (team != Team.BLACK || this.team != Team.WHITE);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isPiece() {
        return true;
    }

    public List<Position> calculateRoute(Position from, Position to) {
        return Position.calculateRoute(from, to);
    }

    public String getName() {
        if (team.equals(Team.WHITE)) {
            return name.getValue().toLowerCase(Locale.ROOT);
        }
        return name.getValue();
    }

    public Team getTeam() {
        return team;
    }
}
