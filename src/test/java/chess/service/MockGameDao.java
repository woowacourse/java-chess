package chess.service;

import static chess.domain.game.Game.LOG_DELIMITER;

import chess.dao.GameDao;
import chess.domain.game.Game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockGameDao implements GameDao {

	private final Map<Integer, Game> games;
	private int id = 0;

	public MockGameDao() {
		this.games = new HashMap<>();
	}

	@Override
	public int save(final Game game) {
		id++;
		games.put(id, new Game(id, game.getName(), game.getCommandLog()));
		return id;
	}

	@Override
	public void update(final Game newGame) {
		int gameId = newGame.getId();
		Game oldGame = games.get(gameId);
		String newLog = String.join(LOG_DELIMITER, oldGame.getCommandLog() + newGame.getCommandLog());
		games.put(gameId, new Game(gameId, oldGame.getName(), newLog));
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
