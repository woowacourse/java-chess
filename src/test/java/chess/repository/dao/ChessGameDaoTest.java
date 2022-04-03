package chess.repository.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

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
	@DisplayName("이름으로 기본키와 상태 찾기")
	void selectWhereName() {
		int key = chessGameDao.insert(TEST_NAME, "READY");

		Map<String, String> result = chessGameDao.selectByName(TEST_NAME);
		assertThat(result.get("game_id")).isEqualTo(String.valueOf(key));
		assertThat(result.get("state")).isEqualTo("READY");
	}
}