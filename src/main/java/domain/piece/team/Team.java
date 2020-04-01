package domain.piece.team;

import java.util.function.Function;

import domain.piece.position.Direction;

public enum Team {
	WHITE("White", (direction) -> Direction.whitePawnDirection().contains(direction)),
	BLACK("Black", (direction) -> Direction.blackPawnDirection().contains(direction));

	private String name;
	private Function<Direction, Boolean> pawnDirectionValidation;

	Team(String name, Function<Direction, Boolean> pawnDirectionValidation) {
		this.name = name;
		this.pawnDirectionValidation = pawnDirectionValidation;
	}

	public static Team changeTurn(Team turn) {
		if (WHITE == turn) {
			return BLACK;
		}
		return WHITE;
	}

	public String getName() {
		return name;
	}

	public Function<Direction, Boolean> getPawnDirectionValidation() {
		return pawnDirectionValidation;
	}
}
