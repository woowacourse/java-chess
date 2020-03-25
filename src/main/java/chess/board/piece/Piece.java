package chess.board.piece;

import chess.board.Vector;

public abstract class Piece {
    protected final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public boolean isBlack() {
        return team.equals(Team.BLACK);
    }

    abstract boolean canMove(Vector vector);

    abstract boolean isSameTeam(Team team);

}
