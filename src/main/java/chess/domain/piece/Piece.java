package chess.domain.piece;

import chess.domain.postion.Position;

public abstract class Piece {
    private Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    abstract void canMove(final Position Source, final Position target);
}
