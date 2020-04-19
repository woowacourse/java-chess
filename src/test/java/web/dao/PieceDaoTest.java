package web.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import web.dto.PieceDto;

class PieceDaoTest {
	private PieceDao pieceDao = PieceDao.getInstance();

	@Test
	void addPiece() throws SQLException {
		PieceDto pieceDto = new PieceDto("r", "white", "a5");
		pieceDao.add(pieceDto);
	}

	@Test
	void findAll() throws SQLException {
		pieceDao.findAll();
	}

	@Test
	void update() throws SQLException {
		pieceDao.update("a5", "a6");
	}
}
