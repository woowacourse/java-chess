package chess.domain;

import chess.domain.board.Team;

public class Turn {

    private boolean isWhite;

    public Turn() {
        this.isWhite = true;
    }

    public Team now() {
        if (isWhite) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public void next() {
        isWhite = !isWhite;
    }
}
