package domain.piece.team;

import java.util.function.Function;

import domain.piece.position.Direction;

public enum Team {
	WHITE((direction) -> Direction.whitePawnDirection().contains(direction)),
	BLACK((direction) -> Direction.blackPawnDirection().contains(direction));

	private Function<Direction, Boolean> directionValidation;

	Team(Function<Direction, Boolean> directionValidation) {
		this.directionValidation = directionValidation;
	}

	public static Team changeTurn(Team turn) {
		if (WHITE == turn) {
			return BLACK;
		}
		return WHITE;
	}

	public Function<Direction, Boolean> getDirectionValidation() {
		return directionValidation;
	}
}
