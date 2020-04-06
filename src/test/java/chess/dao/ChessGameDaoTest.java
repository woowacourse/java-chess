package chess.dao;

import chess.domain.Side;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDaoTest {

	private ChessGameDao chessGameDao;

	@BeforeEach
	void setUp() {
		chessGameDao = new ChessGameDao();
	}

	@Test
	void connect() {
		Connection connection = chessGameDao.getConnection();
		assertThat(connection).isNotNull();

	}

	@Test
	void add() throws Exception {
		chessGameDao.add(Side.BLACK);
	}

	@Test
	void delete() throws Exception {
		chessGameDao.deleteByGameId(0);
	}
}