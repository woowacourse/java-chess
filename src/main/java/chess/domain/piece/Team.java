package chess.domain.piece;

import java.util.function.Function;

public enum Team {
	WHITE(Character::toLowerCase),
	BLACK(Character::toUpperCase);

	private final Function<Character, Character> teamRepresentation;

	Team(Function<Character, Character> teamRepresentation) {
		this.teamRepresentation = teamRepresentation;
	}

	public Function<Character, Character> getTeamRepresentation() {
		return teamRepresentation;
	}
}
