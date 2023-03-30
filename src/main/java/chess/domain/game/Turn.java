package chess.domain.game;

import chess.domain.piece.Team;

public class Turn {

    private final int turn;

    public Turn() {
        this(0);
    }

    public Turn(int turn) {
        validate(turn);
        this.turn = turn;
    }

    private void validate(int turn) {
        if (turn >= 0) {
            return;
        }
        throw new IllegalArgumentException("턴은 0보다 작을 수 없습니다.");
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
