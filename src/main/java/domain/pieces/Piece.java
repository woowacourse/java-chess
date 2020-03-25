package domain.pieces;

import domain.team.Team;

public abstract class Piece {
    private final String initial;
    private final Team team;

    protected Piece(String initial, Team team) {
        this.initial = initial;
        this.team = team;
    }

    public String getInitial() {
        return team.caseInitial(initial);
    }


    @Override
    public String toString() {
        return getInitial();
    }
}
