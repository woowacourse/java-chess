package chess.dto;

import chess.domain.Turn;

public class TurnDto {
	private final String name;

	public TurnDto(Turn name) {
		this.name = name.getTeam().name();
	}

	public String getName() {
		return name;
	}
}
