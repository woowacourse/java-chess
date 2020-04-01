package chess.team;

import java.util.Arrays;

public enum Team {
	BLACK(true, "블랙팀"),
	WHITE(false, "흰색팀");

	private final boolean isBlack;
	private final String name;

	Team(boolean isBlack, String name) {
		this.isBlack = isBlack;
		this.name = name;
	}

	public static Team of(boolean black) {
		return Arrays.stream(values())
			.filter(isBlack -> isBlack.isBlack == black)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("팀을 찾을 수 없습니다."));
	}

	public Team ofOpposingTeam() {
		return Arrays.stream(values())
			.filter(team -> this != team)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("팀을 찾을 수 없습니다."));
	}

	public boolean isBlack() {
		return isBlack;
	}

	public String getName() {
		return this.name;
	}
}
