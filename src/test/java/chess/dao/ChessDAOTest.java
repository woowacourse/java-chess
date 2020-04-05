package chess.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.dto.ChessDTO;
import chess.factory.BoardFactory;

public class ChessDAOTest {

	private ChessDAO chessDAO;

	@BeforeEach
	void setup() {
		chessDAO = new ChessDAO();
	}


	@Test
	void connection() {
		Connection con = chessDAO.getConnection();
		assertThat(con).isNotNull();
	}

	@Test
	void addBoardTest() throws SQLException {
		ChessDTO chessDto = new ChessDTO(BoardFactory.createBoard().toString());
		chessDAO.addBoard(chessDto);
	}

	@Test
	void findTest() throws SQLException {
		ChessDTO chessDto = chessDAO.find();
		System.out.println(chessDto.getRows());
	}
}
