package chess.repository;

import chess.domain.game.Game;

public interface GameDAO {
	Game findById(int userId);

	void update(Game game);
}
