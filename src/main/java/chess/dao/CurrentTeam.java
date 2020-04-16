package chess.dao;

import chess.domain.piece.Team;

import java.util.Objects;

public class CurrentTeam {
    private Team currentTeam;

    public CurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String getCurrentTeam() {
        return this.currentTeam.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrentTeam currentTeam = (CurrentTeam) o;
        return Objects.equals(this.currentTeam, currentTeam.currentTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.currentTeam);
    }
}
