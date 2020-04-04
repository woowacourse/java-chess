package chess.domain.game;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.SuspendFinished;

class FinishedGameTest {
	private Game game;
	private GameState state;
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		state = new SuspendFinished(board, WHITE);
		game = new Game(state);
	}

	@DisplayName("임의 중단 상태에서 start 시, 예외 없이 게임 시작")
	@Test
	void ReadyGameStartTest() {
		assertThatCode(() -> game.start()).doesNotThrowAnyException();
	}

	@DisplayName("임의 중단 상태에서 움직이면, 예외 발생")
	@Test
	void ReadyGameMoveTest() {
		assertThatThrownBy(() -> game.move(Position.of("a2"), Position.of("a4")))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("임의 중단 상태에서 status 호출, 예외 없이 정상 실행")
	@Test
	void ReadyGameStatusTest() {
		assertThatCode(() -> game.status())
			.doesNotThrowAnyException();
	}

	@DisplayName("임의 중단 상태에서 end 호출시 USO 예외 발생")
	@Test
	void ReadyGameEndTest() {
		assertThatThrownBy(() -> game.end())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("임의 중단 상태에서 승자 찾으려 하면 예외 없이 정상 실행")
	@Test
	void ReadyGameFindWinnerTest() {
		assertThatCode(() -> game.findWinner())
			.doesNotThrowAnyException();
	}

	@DisplayName("임의 중단 상태에서 게임이 끝나지 않았는지 묻는다면 false 반환한다.")
	@Test
	void isNotEndTest() {
		assertThat(game.isNotEnd()).isEqualTo(false);
	}
}