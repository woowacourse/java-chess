package domain.piece.team;

import java.util.Arrays;
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

	public static Team of(String turn) {
		return Arrays.stream(Team.values())
			.filter(t -> t.getName().equals(turn))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Turn 입니다."));
	}

	public String getName() {
		return name;
	}

	public Function<Direction, Boolean> getPawnDirectionValidation() {
		return pawnDirectionValidation;
	}
}
