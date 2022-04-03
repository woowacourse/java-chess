package chess.domain;

import chess.domain.command.Command;
import chess.domain.state.GameState;

public class ChessGame {

	private final String name;
	private final GameState state;

	public ChessGame(String name, GameState state) {
		this.name = name;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public ChessGame execute(Command command) {
		GameState updatedState = state.proceed(command);
		return new ChessGame(this.name, updatedState);
	}

	public GameState getState() {
		return this.state;
	}

	public boolean isFinished() {
		return state.isFinished();
	}
}
