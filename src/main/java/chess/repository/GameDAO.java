package chess.repository;

import java.util.Optional;

import chess.domain.game.Game;

public interface GameDAO {
	Optional<Game> findById(int userId);

	void update(Game game);
}
