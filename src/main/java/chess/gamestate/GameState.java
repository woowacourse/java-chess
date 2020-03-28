package chess.gamestate;

import java.util.Arrays;

public enum GameState {
	RUNNING(true),
	END(false);

	private final boolean gameRunning;

	GameState(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public GameState of(boolean hasTwoKings) {
		return Arrays.stream(GameState.values())
			.filter(gameState -> gameState.gameRunning == hasTwoKings)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("승패결과를 찾을 수 없습니다."));
	}
}
