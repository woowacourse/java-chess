package chess.domain.piece;

import chess.domain.TeamColor;

public abstract class Piece {

    private TeamColor teamColor;

    public Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public static class Empty extends Piece {
        public Empty() {
            super(TeamColor.EMPTY);
        }
    }
}
