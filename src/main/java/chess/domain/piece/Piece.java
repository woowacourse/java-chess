package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;

public abstract class Piece {
    private final TeamColor teamColor;

    Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public abstract boolean availablePath(Position start, Position target);
    public abstract List<Position> findAllRouteToTarget(Position start, Position target);

    public abstract boolean canMove(List<Piece> piecesOnRoute, Position start, Position target);

    public abstract boolean isEmpty();

    public boolean isOtherTeam(Piece other) {
        return this.teamColor != other.teamColor;
    }
}
