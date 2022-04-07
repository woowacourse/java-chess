package chess.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.repository.GameRepository;

public class MemoryChessGameRepository implements GameRepository {

	private final Map<String, ChessGame> database = new HashMap<>();

	@Override
	public void save(ChessGame game) {
		database.put(game.getName(), game);
	}

	@Override
	public Optional<ChessGame> findByName(String name) {
		return Optional.ofNullable(database.get(name));
	}

	@Override
	public void updateGame(ChessGame game, Command command) {
		database.put(game.getName(), game);
	}

	@Override
	public void remove(String name) {
		database.remove(name);
	}

	@Override
	public List<String> findAllNames() {
		return new ArrayList<>(database.keySet());
	}
}
