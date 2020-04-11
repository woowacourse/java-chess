package chess.domain.piece;

import java.util.Arrays;

public enum Team {
	BLACK("흑"),
	WHITE("백"),
	NONE("X");

	private final String name;

	Team(String name) {
		this.name = name;
	}

	public static Team of(String name) {
		return Arrays.stream(Team.values())
			.filter(value -> value.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("적절하지 않은 팀 이름 입니다."));
	}

	public Team next() {
		if (this.equals(BLACK)) {
			return WHITE;
		}
		if (this.equals(WHITE)) {
			return BLACK;
		}
		throw new IllegalArgumentException("잘못된 팀 입력입니다.");
	}

	public boolean isEnemy(Team team) {
		if (this.equals(BLACK)) {
			return WHITE.equals(team);
		}
		if (this.equals(WHITE)) {
			return BLACK.equals(team);
		}
		throw new IllegalArgumentException("잘못된 팀 입력입니다.");
	}

	public boolean isNotEnemy(Team team) {
		return !isEnemy(team);
	}

	public String getName() {
		return name;
	}
}
