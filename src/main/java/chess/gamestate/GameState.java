package chess.gamestate;

import java.util.Arrays;

import chess.piece.Piece;
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

	public GameState of(boolean hasTwoKings) {

		return Arrays.stream(GameState.values())
			.filter(gameState -> gameState.gameRunning == hasTwoKings
				&& gameState != this)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("진행결과를 찾을 수 없습니다."));
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public boolean isSameTeam(Piece startingPiece) {
		return startingPiece.isSameTeam(team);
	}
}
