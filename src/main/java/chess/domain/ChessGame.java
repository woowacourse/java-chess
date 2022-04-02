package chess.domain;

import java.util.UUID;

import chess.domain.command.Command;
import chess.domain.state.GameState;

public class ChessGame {

	private final String id;
	private final String name;
	private GameState state;

	public ChessGame(String name, GameState state) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.state = state;
	}

	private ChessGame(String id, String name, GameState state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ChessGame execute(Command command) {
		GameState updatedState = state.proceed(command);
		return new ChessGame(this.id, this.name, updatedState);
	}

	public GameState getState() {
		return this.state;
	}

	public boolean isFinished() {
		return state.isFinished();
	}

	public void updateState(GameState state) {
		this.state = state;
	}
}
