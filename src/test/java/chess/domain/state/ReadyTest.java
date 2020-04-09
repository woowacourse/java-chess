package chess.domain.state;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;

class ReadyTest {
	private GameState state;
	private Board board;
	private Team turn;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		turn = BLACK;
		state = new Ready(board, turn);
	}

	@DisplayName("게임 시작전 상태 객체에서 start 실행시 게임중 상태 객체 반환 테스트")
	@Test
	void start_StartedInstance_test() {
		assertThat(state.start()).isInstanceOf(Started.class);
	}

	@DisplayName("게임 시작전 객체에서 start 실행후, 게임말 초기화 이뤄졌는지 테스트")
	@Test
	void start_initial_pieces_test() {
		state = state.start();
		Board board = state.getBoard();
		Result result = Result.from(board);
		Map<Team, Double> status = result.getStatus();
		assertThat(status).containsOnly(entry(WHITE, 38.0), entry(BLACK, 38.0));
	}

	@DisplayName("게임 시작전 객체에서 체스말 이동 시도시 USO 예외 발생")
	@Test
	void move_unsupportedOperation_test() {
		Position from = Position.of("a1");
		Position to = Position.of("a2");
		assertThatThrownBy(() -> state.move(from, to))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 시작전 객체에서 status호출시 USO 예외 발생")
	@Test
	void status_unsupportedOperation_test() {
		assertThatThrownBy(() -> state.status())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 시작전 객체에서 end 호출시 예외 없이 정상 동작한다.")
	@Test
	void endTest() {
		assertThat(state.end())
			.isInstanceOf(SuspendFinished.class);
	}

	@DisplayName("게임 시작전 객체에게 게임이 안끝났는지 물어볼때 true 반환")
	@Test
	void isNotFinishedTest() {
		assertThat(state.isNotFinished()).isEqualTo(true);
	}
}