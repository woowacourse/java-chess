package chess.webcontroller.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.state.GameState;

public class GameResponseDto {

	private static final String GAME_NAME = "name";
	private static final String TURN = "turn";
	private static final String CHESS_SCORE = "chessScore";

	private final Map<String, Object> value;

	public GameResponseDto(ChessGame game) {
		this.value = new HashMap<>();
		GameState state = game.getState();
		BoardResponseDto dto = BoardResponseDto.from(state.getBoard());
		value.putAll(dto.getValue());

		value.put(GAME_NAME, game.getName());
		try {
			value.put(TURN, state.getColor());
		} catch (UnsupportedOperationException e) {
			value.put(TURN, "");
		}
		try {
			value.put(CHESS_SCORE, state.generateScore());
		} catch (UnsupportedOperationException e) {
			value.put(CHESS_SCORE, "");
		}
	}

	public Map<String, Object> getValue() {
		return value;
	}
}
