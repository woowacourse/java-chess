package chess;

import chess.board.Team;

public class Turn {
    private boolean white;

    public Turn() {
        this.white = true;
    }

    public Team now() {
        if (white) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public void next() {
        white = !white;
    }

}
