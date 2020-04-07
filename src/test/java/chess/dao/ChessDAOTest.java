package chess.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
import chess.dto.ChessDTO;
import chess.domain.factory.BoardFactory;
import chess.view.OutputView;

public class ChessDAOTest {

	private ChessDAO chessDAO;

	@BeforeEach
	void setup() {
		chessDAO = new ChessDAO();
	}

	@Test
	void totalTest() throws SQLException {
		ChessBoard chessBoard = BoardFactory.createBoard();
		ChessDTO chessDTO = new ChessDTO(chessBoard);
		chessDAO.addBoard(chessDTO);

		assertThat(chessDAO.find()).isEqualTo(chessBoard);
		chessBoard.move(Position.of("b2"), Position.of("b4"));
		chessDAO.update(new ChessDTO(chessBoard));
		assertThat(chessDAO.find()).isEqualTo(chessBoard);
		chessDAO.remove(new ChessDTO(chessBoard));
	}

	@Test
	void connection() {
		Connection con = chessDAO.getConnection();
		assertThat(con).isNotNull();
	}

	@Test
	void addBoardTest() throws SQLException {
		ChessDTO chessDto = new ChessDTO(BoardFactory.createBoard());
		chessDAO.addBoard(chessDto);
	}

	@Test
	void findTest() throws SQLException {
		ChessBoard chessBoard = chessDAO.find();
		OutputView.printBoard(chessBoard);
	}

	@Test
	void removeTest() throws SQLException {
		ChessDTO chessDTO = new ChessDTO(BoardFactory.createBoard());
		chessDAO.addBoard(chessDTO);
		chessDAO.remove(chessDTO);
	}

	@Test
	void updateTest() throws SQLException {
		ChessBoard chessBoard = BoardFactory.createBoard();
		chessBoard.move(Position.of("b2"), Position.of("b4"));
		chessDAO.update(new ChessDTO(chessBoard));
	}
}
