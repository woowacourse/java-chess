package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Position start, Position destination);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public Direction teamForwardDirection() {
        return team.getDirection();
    }
}
