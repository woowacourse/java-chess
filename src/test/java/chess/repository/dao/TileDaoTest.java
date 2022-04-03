package chess.repository.dao;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TileDaoTest {

	private static final String TEST_NAME = "does";

	private final TileDao tileDao = new TileDao();
	private final ChessGameDao chessGameDao = new ChessGameDao();

	@AfterEach
	void clear() {
		chessGameDao.delete(TEST_NAME);
	}

	@Test
	@DisplayName("타일 insert 확인")
	void insert() {
		int foreignKey = chessGameDao.insert(TEST_NAME, "READY");
		Map<String, String> tiles = Map.of("a1", "WHITE_PAWN", "b2", "BLACK_KING");

		tileDao.insertAll(tiles, foreignKey);
	}

}