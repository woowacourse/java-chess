package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.command.Start;
import chess.domain.state.Ready;
import chess.domain.state.RunningWhiteTurn;

class GameServiceTest {

	private final GameService gameService = new GameService();

	@Test
	@DisplayName("게임을 기록한다.")
	void saveGame() {
		ChessGame game = new ChessGame("does", new Ready(new HashMap<>()));
		gameService.saveGame(game);
	}

	@Test
	@DisplayName("게임 이름으로 게임을 불러온다.")
	void findGame() {
		ChessGame game = new ChessGame("does", new Ready(new HashMap<>()));
		gameService.saveGame(game);

		ChessGame findGame = gameService.findGame("does");

		assertThat(findGame.getName()).isEqualTo("does");
	}

	@Test
	@DisplayName("게임 상태 업데이트")
	void updateGame() {
		ChessGame game = new ChessGame("does", new Ready(new HashMap<>()));
		gameService.saveGame(game);

		gameService.updateGame(new Start(), "does");
		ChessGame updatedGame = gameService.findGame("does");

		assertThat(updatedGame.getState()).isInstanceOf(RunningWhiteTurn.class);
	}
}