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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.repository.FinishedDrawGameDAO;
import chess.repository.GameDAO;
import chess.repository.ReadyGameDAO;
import chess.repository.StartedGameDAO;
import chess.repository.BlackKingCatchedGameDAO;
import chess.repository.WhiteMoreScoreGameDAO;
import chess.view.dto.requestdto.PositionRequestDTO;
import chess.view.dto.responsedto.BoardDTO;
import chess.view.dto.responsedto.ScoreDTO;

class GameServiceTest {
	private GameService service;
	private GameDAO gameDAO;

	private static Stream<Arguments> gameStateSet() {
		return Stream.of(
			Arguments.of(new ReadyGameDAO(), new PositionRequestDTO("a1", "a3")),
			Arguments.of(new FinishedDrawGameDAO(), new PositionRequestDTO("a1", "a3"))
		);
	}

	private static Stream<Arguments> initialPositionSet() {
		return Stream.of(
			Arguments.arguments(new PositionRequestDTO("a1", "a2"),
				asList(new BoardDTO("a1", "r"),
					new BoardDTO("a2", "p"))
			),
			Arguments.arguments(new PositionRequestDTO("c1", "c5"),
				asList(new BoardDTO("c1", "b"),
					new BoardDTO("c5", "."))
			),
			Arguments.arguments(new PositionRequestDTO("b1", "b8"),
				asList(new BoardDTO("b1", "n"),
					new BoardDTO("b8", "N"))
			)
		);
	}

	@DisplayName("게임 시작전 점수 계산 서비스 실행시, USO 예외 발생")
	@Test
	void calculateScore_Ready_state_exception_test() {
		gameDAO = new ReadyGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> service.calculateScore()).isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임중 점수 계산 서비스 실행시, 정상적으로 DTO 리스트 반환")
	@Test
	void calculateScore_Started_state_normal_test() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.calculateScore()).containsExactlyInAnyOrder(
			new ScoreDTO("black", 38.0),
			new ScoreDTO("white", 38.0)
		);
	}

	@DisplayName("게임 종료후 점수 계산 서비스 실행시, 정상적으로 DTO 리스트 반환")
	@Test
	void calculateScore_Finished_state_normal_test() {
		gameDAO = new FinishedDrawGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.calculateScore()).containsExactlyInAnyOrder(
			new ScoreDTO("black", 38.0),
			new ScoreDTO("white", 38.0)
		);
	}

	@DisplayName("비어있는 보드 판에서 기물 DTO 목록 가져올시, 빈 리스트 반환한다.")
	@Test
	void findAllPiecesOnEmptyBoardTest() {
		gameDAO = new ReadyGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.findAllPiecesOnBoard()).isEqualTo(Collections.emptyList());
	}

	@DisplayName("게임 중이 아닌 경우, move 실행시 예외 발생한다.")
	@ParameterizedTest
	@MethodSource("gameStateSet")
	void moveExceptionTest(GameDAO notPlayingStateDAO, PositionRequestDTO position) {
		service = new GameService(notPlayingStateDAO);
		assertThatThrownBy(() -> service.move(position))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임 중인 상태에서 올바른 차례의 말 움직이면 예외 없이 정상 실행")
	@Test
	void startedGameMoveTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatCode(() -> {
			service.move(new PositionRequestDTO("a2", "a4"));
			service.move(new PositionRequestDTO("a7", "a5"));
		}).doesNotThrowAnyException();
	}

	@DisplayName("게임 중인 상태에서 현재 차례 말을 이동하지 못하는 경로로 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongWayTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> {
			service.move(new PositionRequestDTO("b2", "b4"));
			service.move(new PositionRequestDTO("b7", "b5"));
			service.move(new PositionRequestDTO("a1", "c3"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례 말을 이동하려는 경로 사이 장애물이 있는 경우 illegal argument 예외 발생")
	@Test
	void startedGameMoveWithObstacleTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> service.move(new PositionRequestDTO("a1", "a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례가 아닌 말을 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongTurnTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> {
			service.move(new PositionRequestDTO("a2", "a4"));
			service.move(new PositionRequestDTO("a4", "a5"));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 현재 차례가 아닌 말을 움직이면 illegal argument 예외 발생")
	@Test
	void startedGameMoveWrongTurnTest2() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> service.move(new PositionRequestDTO("a7", "a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("게임 중인 상태에서 빈공간을 출발지로 move 시 illegal argument 예외 발생")
	@Test
	void startedGameMoveEmptySpaceTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> service.move(new PositionRequestDTO("a4", "a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("초기화된 보드 판에서 기물 DTO 목록 가져올시, 보드위 모든 기물 DTO 목록 반환한다.")
	@Test
	void findAllPiecesOnInitialBoardTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.findAllPiecesOnBoard()).contains(
			new BoardDTO("a2", "p"),
			new BoardDTO("b2", "p"),
			new BoardDTO("c2", "p"),
			new BoardDTO("d2", "p"),
			new BoardDTO("e2", "p"),
			new BoardDTO("f2", "p"),
			new BoardDTO("g2", "p"),
			new BoardDTO("h2", "p")
		);
	}

	@DisplayName("인자로 받는 두 위치에 해당하는 좌표, 기물 정보가 담긴 DTO 목록을 가져온다.")
	@ParameterizedTest
	@MethodSource("initialPositionSet")
	void findChangedPiecesOnBoardTest(PositionRequestDTO positionDTO, List<BoardDTO> expected) {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);

		List<BoardDTO> actual = service.findChangedPiecesOnBoard(positionDTO);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("게임 상태 변경이 올바르게 이뤄졌는지 확인한다.")
	@ParameterizedTest
	@CsvSource({"start,started", "end,suspend_finished"})
	void changeStateTest(String request, String expeected) {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		service.changeState(request);
		assertThat(service.getCurrentState().getGameState()).isEqualTo(expeected);
	}

	@DisplayName("게임이 끝난 상태가 아니면 true 반환한다.")
	@ParameterizedTest
	@MethodSource("stateAndIsNotFinishExpectedSets")
	void isNotFinishTest(GameDAO gameDAO, boolean expected) {
		service = new GameService(gameDAO);
		boolean actual = service.isNotFinish();
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> stateAndIsNotFinishExpectedSets() {
		return Stream.of(
			Arguments.of(new ReadyGameDAO(), true),
			Arguments.of(new StartedGameDAO(), true),
			Arguments.of(new FinishedDrawGameDAO(), false)
		);
	}

	@DisplayName("게임이 준비 상태인 경우, 승자를 구하고자 할때 USO 예외 발생한다.")
	@Test
	void getWinnerExceptionTest() {
		gameDAO = new ReadyGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> service.getWinner())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임이 진행중인 상태인 경우, 승자를 구하고자 할때 USO 예외 발생한다.")
	@Test
	void getWinnerWithStartedStateExceptionTest() {
		gameDAO = new StartedGameDAO();
		service = new GameService(gameDAO);
		assertThatThrownBy(() -> service.getWinner())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("게임이 동점으로 끝난 상태인 경우, 승자가 없다.")
	@Test
	void getWinnerWithFinishedStateTest() {
		gameDAO = new FinishedDrawGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.getWinner()).isEqualTo("none");
	}

	@DisplayName("검정 왕이 잡힌 경우, 하얀 팀이 이긴다.")
	@Test
	void getWinnerWithFinishedStateTest2() {
		gameDAO = new BlackKingCatchedGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.getWinner()).isEqualTo("white");
	}

	@DisplayName("하얀 팀이 기물 점수가 더 높은 경우, 하얀 팀이 이긴다.")
	@Test
	void getWinnerWithFinishedStateTest3() {
		gameDAO = new WhiteMoreScoreGameDAO();
		service = new GameService(gameDAO);
		assertThat(service.getWinner()).isEqualTo("white");
	}
}