package chess.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.GameState;
import chess.domain.state.Started;

class StartedStateCommandTest {
	private Board board;
	private GameState gameState;
	private Game game;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		gameState = new Started(board);
		game = new Game(gameState);
	}

	@DisplayName("게임 중인 상태에서 모든 올바른 형식의 명령어를 입력 받는 경우 예외 발생 하지 않는다.")
	@ParameterizedTest
	@ValueSource(strings = {"start", "move a2 a4", "status", "end"})
	void readyState_EndCommandActionTest(String command) {
		assertThatCode(() -> Command.action(game, command))
			.doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 잘못된 형식의 명령어 입력 받는 경우 IllegalArgument 예외 발생 한다.")
	@ParameterizedTest
	@ValueSource(strings = {"move a7 a9", "statos", "star", "enD"})
	void readyStateMove_Status_ActionTest(String invalidCommand) {
		assertThatThrownBy(() -> Command.action(game, invalidCommand))
			.isInstanceOf(IllegalArgumentException.class);
	}
}