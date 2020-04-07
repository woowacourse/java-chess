package chess.domain.dao;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import chess.domain.board.PieceFactory;
import chess.domain.dto.PieceDto;
import chess.domain.piece.Queen;

class BoardDaoTest {
	@Test
	public void CRUDPieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao();
		PieceDto savedPiece = PieceDto.of(A1, PieceFactory.of("r"));

		boardDao.addPiece(savedPiece);
		assertThat(boardDao.findPiece(A1)).isEqualTo(savedPiece);

		boardDao.editPieceByPosition(A1, PieceFactory.of("Q"));
		assertThat(boardDao.findPiece(A1)).isInstanceOf(Queen.class);

		boardDao.deletePieceByPosition(A1);
		assertThat(boardDao.findPiece(A1)).isNull();
	}

	@Test
	public void addAndFindPieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao();
		PieceDto savedPiece = PieceDto.of(A1, PieceFactory.of("r"));

		boardDao.addPiece(savedPiece);

		assertThat(boardDao.findPiece(A1)).isEqualTo(savedPiece);
	}

	@Test
	public void editPieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
		BoardDao boardDao = new BoardDao();
		boardDao.addPiece(PieceDto.of(A1, PieceFactory.of("r")));

		boardDao.editPieceByPosition(A1, PieceFactory.of("Q"));

		assertThat(boardDao.findPiece(A1)).isInstanceOf(Queen.class);
	}

	@Test
	public void deletePieceTest() throws SQLException {
		SQLConnector sqlConnector = new SQLConnector();
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