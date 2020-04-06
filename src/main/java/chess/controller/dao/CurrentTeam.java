package chess.controller.dao;

import java.util.Objects;

public class CurrentTeam {
    private String currentTeam;

    public CurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String getCurrentTeam() {
        return this.currentTeam;
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
        return Objects.equals(currentTeam, currentTeam.currentTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentTeam);
    }
}
