package chess.repository.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.state.Finished;
import chess.domain.state.Ready;

class ChessGameDaoTest {

	private static final String TEST_NAME = "test";
	private final ChessGameDao chessGameDao = new ChessGameDao();
	private final ChessGame game = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));

	@AfterEach
	void clear() {
		chessGameDao.delete(TEST_NAME);
	}

	@Test
	@DisplayName("게임 정보 insert 확인")
	void insert() {
		int key = chessGameDao.insert(game);
		assertThat(key).isGreaterThan(0);
	}

	@Test
	@DisplayName("이름으로 상태 찾기")
	void selectWhereName() {
		chessGameDao.insert(game);

		String result = chessGameDao.selectStateByName(TEST_NAME);
		assertThat(result).isEqualTo("READY");
	}

	@Test
	@DisplayName("상태 업데이트")
	void updateState() {
		chessGameDao.insert(game);

		chessGameDao.updateState(new ChessGame(TEST_NAME, new Finished(new HashMap<>())));
		String result = chessGameDao.selectStateByName(TEST_NAME);
		assertThat(result).isEqualTo("FINISHED");
	}
}