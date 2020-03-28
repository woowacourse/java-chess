package chess.domain;

public class Turn {
    private Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public void switchTurn() {
        team = team.switchTeam();
    }

    public Team getTeam() {
        return team;
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }
}
