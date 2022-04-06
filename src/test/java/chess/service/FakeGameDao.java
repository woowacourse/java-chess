package chess.service;

import chess.dao.GameDao;
import chess.domain.game.Game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeGameDao implements GameDao {

	private final Map<Integer, Game> games;
	private int id = 0;

	public FakeGameDao() {
		this.games = new HashMap<>();
	}

	@Override
	public int save(final Game game) {
		id++;
		games.put(id, new Game(id, game.getName(), game.getCommandLog()));
		return id;
	}

	@Override
	public void update(final int gameId, final String state) {
		Game game = games.get(gameId);
		String newLog = String.join("\n", game.getCommandLog() + state);
		games.put(gameId, new Game(gameId, game.getName(), newLog));
	}

	@Override
	public Game findById(final int gameId) {
		return games.get(gameId);
	}

	@Override
	public List<Game> findAll() {
		return new ArrayList<>(games.values());
	}

	@Override
	public void delete(final int gameId) {
		games.remove(gameId);
	}
}
