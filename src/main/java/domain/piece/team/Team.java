package domain.piece.team;

import java.util.Arrays;

public enum Team {
	WHITE("White"),
	BLACK("Black");

	private String name;

	Team(String name) {
		this.name = name;
	}

	public static Team changeTurn(Team turn) {
		if (WHITE == turn) {
			return BLACK;
		}
		return WHITE;
	}

	public static Team of(String turn) {
		return Arrays.stream(Team.values())
			.filter(t -> t.getName().equals(turn))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Turn 입니다."));
	}

	public String getName() {
		return name;
	}
}
