package chess.view.dto;

import java.util.Objects;

import chess.domain.piece.Team;
import chess.domain.state.StateType;

public class GameDTO {
	String turn;
	String gameState;

	private GameDTO(String turn, String gameState) {
		this.turn = turn;
		this.gameState = gameState;
	}

	public static GameDTO of(Team turn, StateType stateType) {
		return new GameDTO(turn.name().toLowerCase(), stateType.name().toLowerCase());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GameDTO gameDTO = (GameDTO)o;
		return Objects.equals(turn, gameDTO.turn) &&
			Objects.equals(gameState, gameDTO.gameState);
	}

	@Override
	public int hashCode() {
		return Objects.hash(turn, gameState);
	}
}
