package chess.piece;

import chess.Movement;

public abstract class Piece {

    protected final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public abstract Piece move(final Movement movement);

    public boolean isSameTeam(final Piece otherPiece) {
        return this.team.equals(otherPiece.team);
    }

    public abstract String getDisplay();

}
