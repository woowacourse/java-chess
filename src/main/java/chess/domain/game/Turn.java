package chess.domain.game;

import chess.domain.piece.Team;

import java.util.List;

public class Turn {

    private static final int DEFAULT_TEAM_SIZE = 2;
    private static final int START_INDEX = 0;

    private final List<Team> order;
    private int currentIndex;

    public Turn() {
        this.order = List.of(Team.WHITE, Team.BLACK);
        this.currentIndex = START_INDEX;
    }

    public Turn(Team team) {
        this.order = List.of(Team.WHITE, Team.BLACK);
        this.currentIndex = order.indexOf(team);
    }

    public void next() {
        currentIndex = (currentIndex + 1) % DEFAULT_TEAM_SIZE;
    }

    public boolean isCurrent(Team team) {
        return team == order.get(currentIndex);
    }

    public Team getCurrentTeam() {
        return order.get(currentIndex);
    }
}
