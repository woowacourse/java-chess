package web.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import web.dto.TurnDto;

class TurnDaoTest {
	private TurnDao turnDao = TurnDao.getInstance();

	@Test
	void addPiece() throws SQLException {
		TurnDto turnDto = new TurnDto("black");
		turnDao.add(turnDto);
	}

	@Test
	void find() throws SQLException {
		turnDao.find();
	}

	@Test
	void update() throws SQLException {
		TurnDto turnDto = new TurnDto("white");
		turnDao.update(turnDto);
	}
}
