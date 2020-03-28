package chess.domain;

import chess.domain.player.Team;

public class Turn {

    private Team turn;

    private Turn(Team turn) {
        this.turn = turn;
    }

    public static Turn from(Team turn) {
        return new Turn(turn);
    }

    public void switchTurn() {
        turn = turn.toggle();
    }

    public boolean isSameTeam(Team team) {
        return turn.equals(team);
    }
}
