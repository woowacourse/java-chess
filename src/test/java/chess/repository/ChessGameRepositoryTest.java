package chess.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.state.Ready;

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
}