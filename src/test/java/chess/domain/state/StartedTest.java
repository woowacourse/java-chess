package chess.domain.state;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;

class StartedTest {
	private GameState state;
	private Board board;
	private Team turn;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.start();
		turn = BLACK;
		state = new Started(board, turn);
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

	@DisplayName("게임 중, 현재 차례의 말을 제대로 움직이는 경우 예외 없이 정상적으로 동작, Started 객체 반환")
	@Test
	void moveTest() {
		Position from = Position.of("a7");
		Position to = Position.of("a5");
		assertThat(state.move(from, to))
			.isInstanceOf(Started.class);
	}

	@DisplayName("게임 중, 말이 이동을 거쳐 왕을 잡은 경우, 정상적으로 게임 종료 객체 반환 여부 확인")
	@Test
	void move_catchKing_test() {
		Map<Position, Piece> maps = new HashMap<>();
		maps.put(Position.of("a1"), new King(BLACK));
		maps.put(Position.of("a8"), new King(WHITE));
		maps.put(Position.of("a2"), new Rook(BLACK));
		board = new Board(maps);

		state = new Started(board, turn);
		state = state.move(Position.of("a2"), Position.of("a8"));
		assertThat(state).isInstanceOf(KingCatchFinished.class);
	}

	@DisplayName("게임 중, 현재 차례가 아닌 말을 움직이려 하 경우 IllegalArgument 예외 발생")
	@Test
	void move_wrong_turn_exception_test() {
		Position from = Position.of("a2");
		Position to = Position.of("a4");
		assertThatThrownBy(() -> state.move(from, to))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중, 빈칸을 출발점으로 지정하는 경우 IllegalArgument 예외 발생")
	@Test
	void move_from_empty_section_exception_test() {
		Position from = Position.of("a5");
		Position to = Position.of("b6");
		assertThatThrownBy(() -> state.move(from, to))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중, 현재 차례의 말을 이동시키는 경로 사이 장애물이 존재하는 경우 IllegalArgument 예외 발생")
	@Test
	void move_piece_exist_obstacle_exception_test() {
		Position from = Position.of("a8");
		Position to = Position.of("a5");
		assertThatThrownBy(() -> state.move(from, to))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중, 현재 차례의 말이 이동하지 못하는 경로인 경우 IllegalArgument 예외 발생")
	@Test
	void move_impossible_track_exception_test() {
		Position from = Position.of("b8");
		Position to = Position.of("d6");
		assertThatThrownBy(() -> state.move(from, to))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중, 객체에서 status호출시 예외없이 정상적으로 호출")
	@Test
	void statustest() {
		assertThatCode(() -> state.status())
			.doesNotThrowAnyException();
	}

	@DisplayName("게임 중, 객체에서 end 호출시 예외 없이 정상 동작한다.")
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