package chess.domain.chessgame;

import chess.domain.board.Team;

public class Turn {

    private final Team firstTeam;
    private Team currentTeam;

    public Turn(Team currentTeam) {
        this.firstTeam = currentTeam;
        this.currentTeam = currentTeam;
    }

    public Turn(Team firstTeam, Team currentTeam) {
        this.firstTeam = firstTeam;
        this.currentTeam = currentTeam;
    }

    public Team now() {
        return currentTeam;
    }

    public void next() {
        currentTeam = currentTeam.opposingTeam();
    }

    public void restart() {
        currentTeam = firstTeam;
    }
}
