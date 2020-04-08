package web.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.dto.ChessGameDto;

public class BoardDaoTest {
	private BoardDao boardDAO;

	@BeforeEach
	public void setup() {
		boardDAO = new BoardDao();
	}

	@Test
	public void connection() {
		Connection con = boardDAO.getConnection();
		assertNotNull(con);
	}

	@Test
	public void addUser() throws Exception {
		ChessGameDto chessGameDto = new ChessGameDto("5번", "ppppPPP", "white");
		boardDAO.addBoard(chessGameDto);
	}
}
