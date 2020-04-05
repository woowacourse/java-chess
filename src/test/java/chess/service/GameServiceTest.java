package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.Ready;
import chess.view.dto.BoardDTO;
import chess.view.dto.ScoreDTO;

class GameServiceTest {
	private GameService service;
	private Game game;

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
}