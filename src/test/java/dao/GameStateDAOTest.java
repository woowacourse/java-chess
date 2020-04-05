package dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.gamestate.GameState;

class GameStateDAOTest {

	@DisplayName("게임상태 생성 삭제 테스트")
	@Test
	void name() throws SQLException {
		GameStateDAO gameStateDAO = new GameStateDAO();

		gameStateDAO.addGameState(GameState.RUNNING_WHITE_TURN);

		GameState actual = gameStateDAO.findGameState();
		assertThat(actual).isEqualTo(GameState.RUNNING_WHITE_TURN);

		gameStateDAO.deleteGameState(GameState.RUNNING_WHITE_TURN);
	}
}