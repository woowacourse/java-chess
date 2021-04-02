package chess.domain.chessgame;

import chess.domain.board.Team;

public class Turn {

    private Team currentTeam;

    public Turn(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public Team now() {
        return currentTeam;
    }

    public void next() {
        currentTeam = currentTeam.opposingTeam();
    }
}
