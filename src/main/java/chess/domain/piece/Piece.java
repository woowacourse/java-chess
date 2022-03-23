package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private String team;

    public Piece(String team) {
        this.team = team;
    }

    public abstract boolean movable(Position to, Position from);

    public abstract String getName();

    public String getTeam() {
        return this.team;
    }
}
