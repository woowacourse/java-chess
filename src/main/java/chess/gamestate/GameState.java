package chess.gamestate;

import java.util.Arrays;

import chess.team.Team;

public enum GameState {
	RUNNING_BLACK_TURN(true, Team.BLACK),
	RUNNING_WHITE_TURN(true, Team.WHITE),
	END(false);

	private final boolean gameRunning;
	private final Team team;

	GameState(boolean gameRunning, Team team) {
		this.gameRunning = gameRunning;
		this.team = team;
	}

	GameState(boolean gameRunning) {
		this.gameRunning = gameRunning;
		this.team = Team.BLACK;
	}


	public static GameState of(String message) {
		return Arrays.stream(values())
			.filter(gameState -> gameState.name().equals(message))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("해당 상태를 찾을 수 없습니다."));
	}

	public GameState of(boolean hasTwoKings) {
		return Arrays.stream(GameState.values())
			.filter(gameState -> gameState.gameRunning == hasTwoKings
				&& gameState != this)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("진행결과를 찾을 수 없습니다."));
	}

	public GameState findOpposingTeam() {
		return Arrays.stream(values())
			.filter(gameState -> gameState.gameRunning)
			.filter(gameState -> gameState != this)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("반대팀을 찾을 수 없습니다."));
	}


	public boolean isGameRunning() {
		return gameRunning;
	}

	public Team getTeam() {
		return team;
	}
}
