package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.command.Start;
import chess.domain.state.Ready;
import chess.domain.state.RunningWhiteTurn;

class ChessGameServiceTest {

	private static final String TEST_NAME = "does";

	private final ChessGameService gameService = new ChessGameService();

	@AfterEach
	void clear() {
		gameService.deleteGame(TEST_NAME);
	}

	@Test
	@DisplayName("게임을 기록한다.")
	void saveGame() {
		ChessGame game = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		gameService.saveGame(game);
	}

	@Test
	@DisplayName("게임 이름으로 게임을 불러온다.")
	void findGame() {
		ChessGame game = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		gameService.saveGame(game);

		ChessGame findGame = gameService.findGame(TEST_NAME);

		assertThat(findGame.getName()).isEqualTo(TEST_NAME);
	}

	@Test
	@DisplayName("게임 상태 업데이트")
	void updateGame() {
		ChessGame game = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		gameService.saveGame(game);

		gameService.updateGame(new Start(), TEST_NAME);
		ChessGame updatedGame = gameService.findGame(TEST_NAME);

		assertThat(updatedGame.getState()).isInstanceOf(RunningWhiteTurn.class);
	}
}