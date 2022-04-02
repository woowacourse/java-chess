package chess.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.state.Ready;
import chess.domain.state.RunningBlackTurn;
import chess.domain.state.RunningWhiteTurn;

class MemoryGameRepositoryTest {

	private final GameRepository gameRepository = new MemoryGameRepository();

	@Test
	@DisplayName("체스 게임 저장")
	void save() {
		ChessGame game = new ChessGame("does의 게임", new Ready(new HashMap<>()));
		gameRepository.save(game);
	}

	@Test
	@DisplayName("이름으로 저장된 게임 찾아오기")
	void findByName() {
		ChessGame game = new ChessGame("does의 게임", new Ready(new HashMap<>()));
		gameRepository.save(game);

		Optional<ChessGame> findGame = gameRepository.findByName("does의 게임");

		findGame.map(ChessGame::getName)
			.ifPresent(name -> assertThat(name).isEqualTo("does의 게임"));
	}

	@Test
	@DisplayName("게임 이름이 중복 저장되면 예외가 발생한다.")
	void validateDuplicateName() {
		ChessGame game1 = new ChessGame("does의 게임", new Ready(new HashMap<>()));
		ChessGame game2 = new ChessGame("does의 게임", new Ready(new HashMap<>()));
		gameRepository.save(game1);

		assertThatThrownBy(() -> gameRepository.save(game2))
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("상태 업데이트")
	void update() {
		ChessGame newGame = new ChessGame("does의 게임", new Ready(new HashMap<>()));
		gameRepository.save(newGame);

		gameRepository.update(newGame.getId(), new RunningBlackTurn(new HashMap<>()));

		gameRepository.findByName("does의 게임")
			.ifPresent(game -> assertThat(game.getState())
				.isInstanceOf(RunningBlackTurn.class));
	}
}