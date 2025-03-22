package chess.piece;

import chess.Position;
import chess.TeamColor;
import java.util.List;

public abstract class Piece {
    private final TeamColor teamColor;

    Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public abstract boolean availablePath(Position start, Position target);
    public abstract List<Position> findAllRouteToTarget(Position start, Position target);

    public abstract boolean canMove(Piece targetPiece, Position start, Position target);

    public abstract boolean isEmpty();
}
