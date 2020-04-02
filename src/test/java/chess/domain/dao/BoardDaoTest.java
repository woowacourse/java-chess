package chess.domain.dao;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import chess.domain.board.BoardDao;
import chess.domain.board.PieceFactory;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceEditDto;
import chess.domain.piece.Queen;

class BoardDaoTest {
	@Test
	public void connectionTest() {
		BoardDao boardDao = new BoardDao();
		Connection connection = boardDao.getConnection();

		assertThat(connection).isNotNull();
	}

	@Test
	public void CRUDPieceTest() throws SQLException {
		BoardDao boardDao = new BoardDao();
		PieceDto savedPiece = PieceDto.of(A1, PieceFactory.of("r"));

		boardDao.addPiece(savedPiece);
		assertThat(boardDao.findPiece(A1)).isEqualTo(savedPiece);

		PieceEditDto pieceEditDto = new PieceEditDto(A1, PieceFactory.of("Q"));

		boardDao.editPieceByPosition(pieceEditDto);
		assertThat(boardDao.findPiece(A1).getPiece()).isInstanceOf(Queen.class);

		boardDao.deletePieceByPosition(A1);
		assertThat(boardDao.findPiece(A1)).isNull();
	}

	@Test
	public void addAndFindPieceTest() throws SQLException {
		BoardDao boardDao = new BoardDao();
		PieceDto savedPiece = PieceDto.of(A1, PieceFactory.of("r"));

		boardDao.addPiece(savedPiece);

		assertThat(boardDao.findPiece(A1)).isEqualTo(savedPiece);
	}

	@Test
	public void editPieceTest() throws SQLException {
		BoardDao boardDao = new BoardDao();
		boardDao.addPiece(PieceDto.of(A1, PieceFactory.of("r")));
		PieceEditDto pieceEditDto = new PieceEditDto(A1, PieceFactory.of("Q"));

		boardDao.editPieceByPosition(pieceEditDto);

		assertThat(boardDao.findPiece(A1).getPiece()).isInstanceOf(Queen.class);
	}

	@Test
	public void deletePieceTest() throws SQLException {
		BoardDao boardDao = new BoardDao();
		boardDao.addPiece(PieceDto.of(A1, PieceFactory.of("r")));

		boardDao.deletePieceByPosition(A1);

		assertThat(boardDao.findPiece(A1)).isNull();
	}

	@Test
	public void deleteAll() throws SQLException {
		BoardDao boardDao = new BoardDao();
		boardDao.deleteAll();
	}
}