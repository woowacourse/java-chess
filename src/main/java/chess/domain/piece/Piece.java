package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMoveTo(Position target);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }
}
