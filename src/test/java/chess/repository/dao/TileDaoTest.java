package chess.repository.dao;

import static org.assertj.core.api.Assertions.*;

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

	@Test
	@DisplayName("외래키로 tile 조회")
	void selectWhereForeignKey() {
		int foreignKey = chessGameDao.insert(TEST_NAME, "READY");
		Map<String, String> tiles = Map.of("a1", "WHITE_PAWN", "b2", "BLACK_KING");
		tileDao.insertAll(tiles, foreignKey);

		Map<String, String> result = tileDao.selectByGame(foreignKey);

		assertThat(result)
			.containsEntry("a1", "WHITE_PAWN")
			.containsEntry("b2", "BLACK_KING");
	}
}