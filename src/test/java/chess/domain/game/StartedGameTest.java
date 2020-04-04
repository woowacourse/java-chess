package chess.domain.game;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Started;

class StartedGameTest {
	private Game game;
	private GameState state;
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		state = new Started(board, WHITE);
		game = new Game(state);
	}

	@DisplayName("게임 중인 상태에서 start 시, 예외 없이 게임 시작")
	@Test
	void startedGameStartTest() {
		assertThatCode(() -> game.start()).doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 올바른 차례의 말 움직이면 예외 없이 정상 실행")
	@Test
	void startedGameMoveTest() {
		assertThatCode(() -> {
			game.move(Position.of("a2"), Position.of("a4"));
			game.move(Position.of("a7"), Position.of("a5"));
		}).doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 현재 차례 말을 이동하지 못하는 경로로 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongWayTest() {
		assertThatThrownBy(() -> {
			game.move(Position.of("b2"), Position.of("b4"));
			game.move(Position.of("b7"), Position.of("b5"));
			game.move(Position.of("a1"), Position.of("c3"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례 말을 이동하려는 경로 사이 장애물이 있는 경우 illegal argument 예외 발생")
	@Test
	void startedGameMoveWithObstacleTest() {
		assertThatThrownBy(() -> {
			game.move(Position.of("a1"), Position.of("a5"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례가 아닌 말을 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongTurnTest() {
		assertThatThrownBy(() -> {
			game.move(Position.of("a2"), Position.of("a4"));
			game.move(Position.of("a4"), Position.of("a5"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례가 아닌 말을 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongTurnTest2() {
		assertThatThrownBy(() -> game.move(Position.of("a7"), Position.of("a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 빈공간을 출발지로 move 시 illegal argument 예외 발생")
	@Test
	void startedGameMoveEmptySpaceTest() {
		assertThatThrownBy(() -> game.move(Position.of("a4"), Position.of("a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 status 호출시, 예외 발생하지 않는다.")
	@Test
	void startedGameStatusTest() {
		assertThatCode(() -> game.status())
			.doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 end 호출시 예외 없이 게임 종료")
	@Test
	void startedGameEndTest() {
		assertThatCode(() -> game.end()).doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 승자 찾으려 하면 USO 예외 발생")
	@Test
	void startedGameFindWinnerTest() {
		assertThatThrownBy(() -> game.findWinner())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 중인 상태에서 게임이 끝나지 않았는지 묻는다면 true 반환한다.")
	@Test
	void startedIsNotEndTest() {
		assertThat(game.isNotEnd()).isEqualTo(true);
	}
}