package chess.team;

import java.util.Arrays;

public enum Team {
	BLACK(true),
	WHITE(false);

	private final boolean isBlack;

	Team(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public static Team of(boolean black) {
		return Arrays.stream(values())
			.filter(isBlack -> isBlack.isBlack == black)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("팀을 찾을 수 없습니다."));
	}

	public boolean isBlack() {
		return isBlack;
	}
}
