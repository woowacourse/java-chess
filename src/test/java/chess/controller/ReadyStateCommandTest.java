package chess.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

class ReadyStateCommandTest {
	private Board board;
	private GameState gameState;
	private Game game;

	@BeforeEach
	void setUp() {
		board = new Board();
		gameState = new Ready(board);
		game = new Game(gameState);
	}

	@DisplayName("게임 준비 상태에서 start, end 명령어를 입력 받는 경우 예외 발생 하지 않는다.")
	@ParameterizedTest
	@ValueSource(strings = {"start", "end"})
	void readyState_EndCommandActionTest(String command) {
		assertThatCode(() -> Command.action(game, command))
			.doesNotThrowAnyException();
	}

	@DisplayName("게임 준비 상태에서 move, status 명령어를 입력 받는 경우 USO 예외 발생 한다.")
	@ParameterizedTest
	@ValueSource(strings = {"move a1 a3", "status"})
	void readyStateMove_Status_ActionTest(String invalidCommand) {
		assertThatThrownBy(() -> Command.action(game, invalidCommand))
			.isInstanceOf(UnsupportedOperationException.class);
	}
}