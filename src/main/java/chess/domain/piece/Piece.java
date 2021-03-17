package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;

public abstract class Piece {

    private TeamColor teamColor;
    private boolean moved;

    public Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public String nameByTeamColor() {
        if (teamColor == TeamColor.BLACK) {
            return name().toUpperCase();
        }
        return name();
    }

    public void changeMoveState() {
        moved = true;
    }

    protected TeamColor teamColor() {
        return teamColor;
    }

    protected boolean notMoved() {
        return !moved;
    }

    protected abstract String name();

    public abstract boolean movable(Position currentPosition, Position targetPosition);
}
