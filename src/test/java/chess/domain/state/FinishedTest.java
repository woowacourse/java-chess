package chess.domain.state;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;

class FinishedTest {
	private GameState state;
	private Board board;
	private Team turn;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		turn = BLACK;
		state = new SuspendFinished(board, turn);
	}

	@DisplayName("종료된 게임 상태 객체에서 start 실행시 게임중 상태 객체 반환 테스트")
	@Test
	void start_StartedInstance_test() {
		assertThat(state.start()).isInstanceOf(Started.class);
	}

	@DisplayName("종료된 게임 객체에서 start 실행후, 게임말 초기화 이뤄졌는지 테스트")
	@Test
	void start_initial_pieces_test() {
		state = state.start();
		Board board = state.getBoard();
		Result status = Result.from(board);
		assertThat(status.getStatus()).containsOnly(entry(WHITE, 38.0), entry(BLACK, 38.0));
	}

	@DisplayName("종료상태의 객체에서 체스말 이동 시도시 USO 예외 발생")
	@Test
	void move_unsupportedOperation_test() {
		Position from = Position.of("a1");
		Position to = Position.of("a2");
		assertThatThrownBy(() -> state.move(from, to))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 종료 상태 객체에서 status를 호출해도 예외가 발생하지 않는다.")
	@Test
	void statusTest() {
		assertThatCode(() -> state.status()).doesNotThrowAnyException();
	}

	@DisplayName("게임 종료 상태 객체에서 end 호출시 USO 예외 발생한다.")
	@Test
	void end_throw_unsupported_operation_exception_Test() {
		assertThatThrownBy(() -> state.end())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 종료 객체에게 게임이 안끝났는지 물어볼때 false 반환")
	@Test
	void isNotFinishedTest() {
		assertThat(state.isNotFinished()).isEqualTo(false);
	}
}