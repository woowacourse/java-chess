package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public abstract class Piece {

    protected Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean isMovable(Position source, Position target);
    public abstract List<Position> findPath(Position source, Position target);

    public boolean isSameTeam(Piece otherPiece) {
        return team.isSameTeam(otherPiece.team);
    }
}
