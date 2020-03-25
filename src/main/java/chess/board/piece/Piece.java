package chess.board.piece;

import chess.board.Variation;

public abstract class Piece {
    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    abstract boolean canMove(Variation variation);

    abstract boolean isSameTeam(Team team);
}
