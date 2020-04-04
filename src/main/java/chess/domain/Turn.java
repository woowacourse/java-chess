package chess.domain;

public class Turn {
	private Team team;

	public Turn(Team team) {
		this.team = team;
	}

	public Turn switchTurn() {
		team = team.switchTeam();
		return new Turn(team);
	}

	public Team getTeam() {
		return team;
	}

	public boolean isSameTeam(Team team) {
		return this.team.equals(team);
	}
}
