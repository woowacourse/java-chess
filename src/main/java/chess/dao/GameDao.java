package chess.dao;

import chess.domain.game.Game;
import java.util.List;

public interface GameDao {

	int save(final String name, final String state);

	void update(final int gameId, final String state);

	Game findById(final int gameId);

	List<Game> findAll();

}
