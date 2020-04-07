package chess.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;

class ChessGameDaoTest {
	private ChessGameDao chessGameDao;

	@BeforeEach
	void setup() {
		chessGameDao = new InMemoryChessGameDao();
	}

	@Test
	void create() throws Exception {
		int chessGameId = chessGameDao.create();
		assertThat(chessGameId).isEqualTo(1);
	}

	@Test
	void findAll() throws Exception {
		chessGameDao.create();
		chessGameDao.create();
		chessGameDao.create();
		assertThat(chessGameDao.findAll().size()).isEqualTo(3);
	}

	@Test
	void findById() throws Exception {
		int chessGameId = chessGameDao.create();
		assertThat(chessGameDao.findById(chessGameId)).isInstanceOf(ChessGame.class);
	}

	@Test
	void update() throws Exception {
		int chessGameId = chessGameDao.create();
		ChessGame chessGame = new ChessGame(new Ready());
		chessGameDao.update(chessGameId, chessGame);
		assertThat(chessGameDao.findById(chessGameId)).isEqualTo(chessGame);
	}

	@Test
	void deleteById() throws Exception {
		int chessGameId = chessGameDao.create();
		chessGameDao.deleteById(chessGameId);
		assertThat(chessGameDao.findById(chessGameId)).isNull();
	}
}