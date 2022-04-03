package chess.repository.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

	private static final String TEST_NAME = "does";
	private final ChessGameDao chessGameDao = new ChessGameDao();

	@AfterEach
	void clear() {
		chessGameDao.delete(TEST_NAME);
	}

	@Test
	@DisplayName("게임 정보 insert 확인")
	void insert() {
		int key = chessGameDao.insert(TEST_NAME, "READY");
		assertThat(key).isGreaterThan(0);
	}
}