package chess.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.state.Ready;
import chess.domain.state.RunningBlackTurn;

class ChessGameRepositoryTest {

	private static final String TEST_NAME = "does";

	private final GameRepository gameRepository = new ChessGameRepository();

	@AfterEach
	void clear() {
		gameRepository.remove(TEST_NAME);
	}

	@Test
	@DisplayName("체스 게임 저장")
	void save() {
		ChessGame game = new ChessGame(TEST_NAME, new Ready(BoardInitializer.generate()));
		gameRepository.save(game);
	}

	@Test
	@DisplayName("이름으로 저장된 게임 찾아오기")
	void findByName() {
		ChessGame chessGame = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		gameRepository.save(chessGame);

		Optional<ChessGame> findGame = gameRepository.findByName(TEST_NAME);

		findGame.ifPresentOrElse(game -> assertThat(game.getName()).isEqualTo(TEST_NAME),
			() -> {throw new IllegalStateException();});
	}

	@Test
	@DisplayName("없는 이름으로 게임을 찾으면 empty를 반환한다.")
	void findByNameNotExist() {
		Optional<ChessGame> findGame = gameRepository.findByName(TEST_NAME);
		assertThat(findGame.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("게임 이름이 중복 저장되면 예외가 발생한다.")
	void validateDuplicateName() {
		ChessGame game1 = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		ChessGame game2 = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		gameRepository.save(game1);

		assertThatThrownBy(() -> gameRepository.save(game2))
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("상태 업데이트")
	void update() {
		ChessGame newGame = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));
		gameRepository.save(newGame);

		gameRepository.update(newGame.getName(), new RunningBlackTurn(new HashMap<>()));

		gameRepository.findByName(TEST_NAME)
			.ifPresent(game -> assertThat(game.getState())
				.isInstanceOf(RunningBlackTurn.class));
	}
}