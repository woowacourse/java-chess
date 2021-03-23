package chess.domain;

import chess.domain.board.Team;

public class Turn {

    private boolean isWhiteTurn;

    public Turn() {
        this.isWhiteTurn = true;
    }

    public Team now() {
        if (isWhiteTurn) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public void next() {
        isWhiteTurn = !isWhiteTurn;
    }
}
