package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MoveDaoTest {
	@Test
	void crud() throws SQLException {
		GamesDao gamesDao = new GamesDao();
		int gameId = gamesDao.createGame("kyle", "pobi");
		MoveDao moveDao = new MoveDao();
		moveDao.save("a2", "a4", gameId);
		Map<String, String> moves = moveDao.findMovesByGameId(gameId);
		for (Map.Entry<String, String> move : moves.entrySet()) {
			assertThat(move.getKey()).isEqualTo("a2");
			assertThat(move.getValue()).isEqualTo("a4");
		}
	}
}