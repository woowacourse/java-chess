package chess.domain;

import chess.domain.board.Team;

public class Turn {

    private Team currentTeam;

    public Turn(Team firstTeam) {
        this.currentTeam = firstTeam;
    }

    public Team now() {
        return currentTeam;
    }

    public void next() {
        currentTeam = currentTeam.opposingTeam();
    }
}
