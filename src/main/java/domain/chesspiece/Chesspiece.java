package domain.chesspiece;

import domain.team.Team;

public abstract class Chesspiece {
    private final String initial;
    private final Team team;

    protected Chesspiece(String initial, Team team) {
        this.initial = initial;
        this.team = team;
    }

    public String getInitial() {
        return team.caseInitial(initial);
    }

    @Override
    public String toString() {
        return initial;
    }
}
