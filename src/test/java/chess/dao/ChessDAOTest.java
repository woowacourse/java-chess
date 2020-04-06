package chess.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
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
		ChessDTO chessDto = new ChessDTO(1, BoardFactory.createBoard().toString(), true);
		chessDAO.addBoard(chessDto);
	}

	@Test
	void findTest() throws SQLException {
		ChessBoard chessBoard = chessDAO.find();
		System.out.println(chessBoard);
	}

	@Test
	void removeAllTest() throws SQLException {
		chessDAO.removeAll();
	}

	@Test
	void updateTest() throws SQLException {
		ChessBoard chessBoard = chessDAO.find();
		chessBoard.move(Position.of("b2"), Position.of("b4"));
		chessDAO.update(new ChessDTO(chessBoard));

	}
}
