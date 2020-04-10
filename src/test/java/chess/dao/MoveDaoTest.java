package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MoveDaoTest {
	@Test
	void crud() throws SQLException {
		GamesDao gamesDao = new GamesDao();
		int gameId = gamesDao.createGame("kyle", "pobi");
		MoveDao moveDao = new MoveDao();
		moveDao.save("a2", "a4", gameId);
		Map<Integer, List<String>> moves = moveDao.findMovesByGameId(gameId);
		for (List<String> value : moves.values()) {
			assertThat(value.get(0)).isEqualTo("a2");
			assertThat(value.get(1)).isEqualTo("a4");
		}
	}
}