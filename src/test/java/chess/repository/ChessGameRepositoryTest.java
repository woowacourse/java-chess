package chess.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.state.Ready;

class ChessGameRepositoryTest {

	private final GameRepository gameRepository = new ChessGameRepository();

	@AfterEach
	void clear() {
		gameRepository.removeAll();
	}

	@Test
	@DisplayName("game 테이블에 저장")
	void save() {
		ChessGame game = new ChessGame("does", new Ready(BoardInitializer.generate()));
		gameRepository.save(game);
	}
}