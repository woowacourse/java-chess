package chess.dao;

import chess.domain.dto.PieceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardDaoTest {

	private ChessBoardDao chessBoardDao;

	@BeforeEach
	void setUp() {
		chessBoardDao = new ChessBoardDao();
	}

	@Test
	void connect() {
		Connection connection = chessBoardDao.getConnection();
		assertThat(connection).isNotNull();

	}

	@Test
	void add() throws Exception {
		chessBoardDao.add(new PieceDto("\u265A", 1, 1), 1);
	}

	@Test
	void delete() throws Exception {
		chessBoardDao.deleteByGameId(0);
	}

	@Test
	void crud() throws Exception {
		PieceDto pieceDto = new PieceDto("\u265A", 1, 1);
		chessBoardDao.deleteByGameId(0);
		chessBoardDao.add(pieceDto, 0);
		assertThat(chessBoardDao.findByGameId(0)).contains(pieceDto);
	}

	@Test
	void findWithException() throws Exception {
		chessBoardDao.deleteByGameId(0);
		assertThatThrownBy(() -> chessBoardDao.findByGameId(0))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("id에 해당하는 정보가 없습니다.");
	}
}