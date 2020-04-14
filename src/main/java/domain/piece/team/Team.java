package domain.piece.team;

import java.util.Arrays;

import domain.piece.Pawn;

public enum Team {
	BLACK("Black", Pawn.BLACK_START_RANK_INDEX),
	WHITE("white", Pawn.WHITE_START_RANK_INDEX);

	private String name;
	private int startRankIndex;

	Team(String name, int startRankIndex) {
		this.name = name;
		this.startRankIndex = startRankIndex;
	}

	public static Team of(String name) {
		return Arrays.stream(values())
			.filter(team -> team.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당 팀이 존재하지 않습니다."));
	}

	public static Team changeTurn(Team turn) {
		if (WHITE == turn) {
			return BLACK;
		}
		return WHITE;
	}

	public static boolean isWhite(Team team) {
		return WHITE.equals(team);
	}

	public boolean isOurTeam(Team team) {
		return this.equals(team);
	}

	public String getName() {
		return name;
	}

	public int getStartRankIndex() {
		return startRankIndex;
	}
}
