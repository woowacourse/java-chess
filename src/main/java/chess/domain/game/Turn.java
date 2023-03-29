package chess.domain.game;

import chess.domain.piece.Team;

public class Turn {

    private final int turn;

    public Turn() {
        this.turn = 0;
    }

    public Turn(int turn) {
        this.turn = turn;
    }

    public Turn next() {
        return new Turn(turn + 1);
    }

    public Team getCurrentTeam() {
        if (turn % 2 == 0) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public int getTurn() {
        return turn;
    }
}
