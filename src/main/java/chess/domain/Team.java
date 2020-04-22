package chess.domain;

import java.util.Arrays;

public enum Team {
	WHITE,
	BLACK,
	NONE;

	public static Team of(String team) {
		return Arrays.stream(values())
			.filter(value -> value.name().equals(team.toUpperCase()))
			.findFirst()
			.orElseThrow(()-> new IllegalArgumentException("존재하지 않는 팀입니다."));
	}

	public Team switchTeam() {
		if (this == WHITE) {
			return BLACK;
		}
		return WHITE;
	}

	public boolean isSameTeam(Team team) {
		return this == team;
	}

	public boolean isNotSameTeam(Team team) {
		return !isSameTeam(team);
	}
}
