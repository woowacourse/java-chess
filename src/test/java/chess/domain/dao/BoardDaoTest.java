package chess.domain.dao;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import chess.domain.board.PieceFactory;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PieceEditDto;
import chess.domain.piece.Queen;

class BoardDaoTest {
	@Test
	public void connectionTest() {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao(sqlConnector);
		Connection connection = boardDao.getConnection();

		assertThat(connection).isNotNull();
	}

	@Test
	public void CRUDPieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao(sqlConnector);
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
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao(sqlConnector);
		PieceDto savedPiece = PieceDto.of(A1, PieceFactory.of("r"));

		boardDao.addPiece(savedPiece);

		assertThat(boardDao.findPiece(A1)).isEqualTo(savedPiece);
	}

	@Test
	public void editPieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao(sqlConnector);
		boardDao.addPiece(PieceDto.of(A1, PieceFactory.of("r")));
		PieceEditDto pieceEditDto = new PieceEditDto(A1, PieceFactory.of("Q"));

		boardDao.editPieceByPosition(pieceEditDto);

		assertThat(boardDao.findPiece(A1).getPiece()).isInstanceOf(Queen.class);
	}

	@Test
	public void deletePieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao(sqlConnector);
		boardDao.addPiece(PieceDto.of(A1, PieceFactory.of("r")));

		boardDao.deletePieceByPosition(A1);

		assertThat(boardDao.findPiece(A1)).isNull();
	}

	@Test
	public void deleteAll() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao(sqlConnector);
		boardDao.deleteAll();
	}
}