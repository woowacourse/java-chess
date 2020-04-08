package chess.domain.game.dao;

import java.sql.SQLException;

import chess.domain.game.Game;

public interface GameDAO {
	Game findById(int userId) throws SQLException;

	void update(Game game) throws SQLException;
}
