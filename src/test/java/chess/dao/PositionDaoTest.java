package chess.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class PositionDaoTest {
	private final PositionDao positionDao = new PositionDao(new RepositoryConnector());

	@Test
	void crud() throws SQLException {
		positionDao.addPosition(1, Position.of("2a"));
	}
}