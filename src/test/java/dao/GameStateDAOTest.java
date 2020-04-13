package dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.gamestate.GameState;

class GameStateDAOTest {

	private RoomDAO roomDAO;
	private GameStateDAO gameStateDAO;

	@BeforeEach
	void setUp() throws SQLException {
		gameStateDAO = new GameStateDAO();
		roomDAO = new RoomDAO();
		roomDAO.createRoom(1000);
	}

	@AfterEach
	void tearDown() throws SQLException {
		roomDAO.delete(String.valueOf(1000));
	}

	@DisplayName("게임상태 생성 삭제 테스트")
	@Test
	void name() throws SQLException {
		gameStateDAO.addGameState(1000L, GameState.RUNNING_WHITE_TURN);

		GameState actual = gameStateDAO.findGameState(1000L);
		assertThat(actual).isEqualTo(GameState.RUNNING_WHITE_TURN);

	}

	@DisplayName("GameState 탐색 테스트")
	@Test
	void findGameState() throws SQLException {
		gameStateDAO.addGameState(1000L, GameState.RUNNING_WHITE_TURN);
		GameState actual = gameStateDAO.findGameState(1000L);

		assertThat(actual).isEqualTo(GameState.RUNNING_WHITE_TURN);
	}

	@DisplayName("GameState 변경 테스트")
	@Test
	void updateMessage() throws SQLException {
		gameStateDAO.addGameState(1000L, GameState.RUNNING_WHITE_TURN);

		gameStateDAO.updateMessage(1000L, GameState.RUNNING_WHITE_TURN, GameState.END);
		GameState actual = gameStateDAO.findGameState(1000L);

		assertThat(actual).isEqualTo(GameState.END);
	}
}