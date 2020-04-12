package web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.dto.ChessGameDto;

class BoardDaoTest {
	private BoardDao boardDAO;

	@BeforeEach
	void setup() {
		boardDAO = new BoardDao();
	}

	@Test
	void addUser() throws Exception {
		ChessGameDto chessGameDto = new ChessGameDto("7번", "ppppPPP", "white");
		boardDAO.addBoard(chessGameDto);
	}
}
