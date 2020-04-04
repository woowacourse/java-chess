package chess.domain.game;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

class ReadyGameTest {
	private Game game;
	private GameState state;
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		state = new Ready(board, WHITE);
		game = new Game(state);
	}

	@DisplayName("준비상태 게임에서 start 시, 예외 없이 게임 시작")
	@Test
	void ReadyGameStartTest() {
		assertThatCode(() -> game.start()).doesNotThrowAnyException();
	}

	@DisplayName("게임 준비 상태에서 움직이면, 예외 발생")
	@Test
	void ReadyGameMoveTest() {
		assertThatThrownBy(() -> game.move(Position.of("a2"), Position.of("a4")))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 준비 상태에서 status 호출, 예외 발생")
	@Test
	void ReadyGameStatusTest() {
		assertThatThrownBy(() -> game.status())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 준비 상태에서 end 호출시 예외 없이 게임 종료")
	@Test
	void ReadyGameEndTest() {
		assertThatCode(() -> game.end()).doesNotThrowAnyException();
	}

	@DisplayName("게임 준비 상태에서 승자 찾으려 하면 USO 예외 발생")
	@Test
	void ReadyGameFindWinnerTest() {
		assertThatThrownBy(() -> game.findWinner())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 준비 상태에서 게임이 끝나지 않았는지 묻는다면 true 반환한다.")
	@Test
	void isNotEndTest() {
		assertThat(game.isNotEnd()).isEqualTo(true);
	}
}