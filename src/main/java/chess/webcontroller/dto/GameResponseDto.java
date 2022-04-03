package chess.webcontroller.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.state.GameState;

public class GameResponseDto {

	private static final String GAME_NAME = "GAME_NAME";
	private static final String TURN = "TURN";
	private static final String CHESS_SCORE = "CHESS_SCORE";

	private final Map<String, Object> value;

	public GameResponseDto(ChessGame game) {
		this.value = new HashMap<>();
		GameState state = game.getState();
		BoardResponseDto dto = BoardResponseDto.from(state.getBoard());
		value.putAll(dto.getValue());

		value.put(GAME_NAME, game.getName());
		value.put(TURN, state.getColor());
		value.put(CHESS_SCORE, state.generateScore());
	}

	public Map<String, Object> getValue() {
		return value;
	}
}
