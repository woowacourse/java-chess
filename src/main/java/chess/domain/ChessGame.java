package chess.domain;

import java.util.UUID;

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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
