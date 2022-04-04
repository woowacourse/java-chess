package chess.repository.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

	private static final String TEST_NAME = "test";
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

	@Test
	@DisplayName("이름으로 상태 찾기")
	void selectWhereName() {
		chessGameDao.insert(TEST_NAME, "READY");

		String result = chessGameDao.selectStateByName(TEST_NAME);
		assertThat(result).isEqualTo("READY");
	}

	@Test
	@DisplayName("상태 업데이트")
	void updateState() {
		chessGameDao.insert(TEST_NAME, "READY");

		chessGameDao.updateState(TEST_NAME, "FINISHED");
		String result = chessGameDao.selectStateByName(TEST_NAME);
		assertThat(result).isEqualTo("FINISHED");
	}
}