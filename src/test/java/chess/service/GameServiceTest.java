package chess.service;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.Started;
import chess.domain.state.SuspendFinished;
import chess.view.dto.BoardDTO;
import chess.view.dto.ScoreDTO;

class GameServiceTest {
	private GameService service;
	private Game game;

	private static Stream<Arguments> gameStateSet() {
		return Stream.of(
			Arguments.of(new Ready(new Board()), Position.of("a1"), Position.of("a3")),
			Arguments.of(new SuspendFinished(new Board(), Team.WHITE), Position.of("a1"), Position.of("a3"))
		);
	}

	private static Stream<Arguments> initialPositionSet() {
		return Stream.of(
			Arguments.arguments(Position.of("a1"), Position.of("a2"),
				asList(new BoardDTO("a1", "r", "white"),
					new BoardDTO("a2", "p", "white"))
			),
			Arguments.arguments(Position.of("c1"), Position.of("c5"),
				asList(new BoardDTO("c1", "b", "white"),
					new BoardDTO("c5", ".", "none"))
			),
			Arguments.arguments(Position.of("b1"), Position.of("b8"),
				asList(new BoardDTO("b1", "n", "white"),
					new BoardDTO("b8", "n", "black"))
			)
		);
	}

	@DisplayName("게임 시작전 점수 계산 서비스 실행시, USO 예외 발생")
	@Test
	void calculateScore_Ready_state_exception_test() {
		game = new Game(new Ready(new Board()));
		service = new GameService(game);
		assertThatThrownBy(() -> service.calculateScore()).isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임중 점수 계산 서비스 실행시, 정상적으로 DTO 리스트 반환")
	@Test
	void calculateScore_Started_state_normal_test() {
		game = new Game(new Ready(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThat(service.calculateScore()).containsExactlyInAnyOrder(
			new ScoreDTO("black", 38.0),
			new ScoreDTO("white", 38.0)
		);
	}

	@DisplayName("게임 종료후 점수 계산 서비스 실행시, 정상적으로 DTO 리스트 반환")
	@Test
	void calculateScore_Finished_state_normal_test() {
		game = new Game(new Ready(new Board()));
		service = new GameService(game);
		service.startNewGame();
		game.end();
		assertThat(service.calculateScore()).containsExactlyInAnyOrder(
			new ScoreDTO("black", 38.0),
			new ScoreDTO("white", 38.0)
		);
	}

	@DisplayName("비어있는 보드 판에서 기물 DTO 목록 가져올시, 빈 리스트 반환한다.")
	@Test
	void findAllPiecesOnEmptyBoardTest() {
		game = new Game(new Ready(new Board()));
		service = new GameService(game);
		assertThat(service.findAllPiecesOnBoard()).isEqualTo(Collections.emptyList());
	}

	@DisplayName("게임 중이 아닌 경우, move 실행시 예외 발생한다.")
	@ParameterizedTest
	@MethodSource("gameStateSet")
	void moveExceptionTest(GameState notPlayingState, Position from, Position to) {
		game = new Game(notPlayingState);
		service = new GameService(game);
		assertThatThrownBy(() -> service.move(from, to))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 중인 상태에서 올바른 차례의 말 움직이면 예외 없이 정상 실행")
	@Test
	void startedGameMoveTest() {
		game = new Game(new Started(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThatCode(() -> {
			game.move(Position.of("a2"), Position.of("a4"));
			game.move(Position.of("a7"), Position.of("a5"));
		}).doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 현재 차례 말을 이동하지 못하는 경로로 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongWayTest() {
		game = new Game(new Started(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThatThrownBy(() -> {
			game.move(Position.of("b2"), Position.of("b4"));
			game.move(Position.of("b7"), Position.of("b5"));
			game.move(Position.of("a1"), Position.of("c3"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례 말을 이동하려는 경로 사이 장애물이 있는 경우 illegal argument 예외 발생")
	@Test
	void startedGameMoveWithObstacleTest() {
		game = new Game(new Started(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThatThrownBy(() -> game.move(Position.of("a1"), Position.of("a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례가 아닌 말을 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongTurnTest() {
		game = new Game(new Started(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThatThrownBy(() -> {
			game.move(Position.of("a2"), Position.of("a4"));
			game.move(Position.of("a4"), Position.of("a5"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례가 아닌 말을 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongTurnTest2() {
		game = new Game(new Started(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThatThrownBy(() -> game.move(Position.of("a7"), Position.of("a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 빈공간을 출발지로 move 시 illegal argument 예외 발생")
	@Test
	void startedGameMoveEmptySpaceTest() {
		game = new Game(new Started(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThatThrownBy(() -> game.move(Position.of("a4"), Position.of("a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("초기화된 보드 판에서 기물 DTO 목록 가져올시, 보드위 모든 기물 DTO 목록 반환한다.")
	@Test
	void findAllPiecesOnInitialBoardTest() {
		game = new Game(new Ready(new Board()));
		service = new GameService(game);
		service.startNewGame();
		assertThat(service.findAllPiecesOnBoard()).contains(
			new BoardDTO("a2", "p", "white"),
			new BoardDTO("b2", "p", "white"),
			new BoardDTO("c2", "p", "white"),
			new BoardDTO("d2", "p", "white"),
			new BoardDTO("e2", "p", "white"),
			new BoardDTO("f2", "p", "white"),
			new BoardDTO("g2", "p", "white"),
			new BoardDTO("h2", "p", "white")
		);
	}

	@DisplayName("인자로 받는 두 위치에 해당하는 좌표, 기물 정보가 담긴 DTO 목록을 가져온다.")
	@ParameterizedTest
	@MethodSource("initialPositionSet")
	void findChangedPiecesOnBoardTest(Position from, Position to, List<BoardDTO> expected) {
		game = new Game(new Ready(new Board()));
		service = new GameService(game);
		service.startNewGame();

		List<BoardDTO> actual = service.findChangedPiecesOnBoard(from, to);
		assertThat(actual).isEqualTo(expected);
	}
}