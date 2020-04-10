package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;
import chess.domain.state.BoardRepository;

class BoardTest {
	@Test
	void isKingDeadTest() {
		Board board = BoardRepository.create();
		board.move(Position.of("c2"), Position.of("c4"), new Turn(Team.WHITE));
		board.move(Position.of("b7"), Position.of("b5"), new Turn(Team.BLACK));
		board.move(Position.of("c4"), Position.of("c5"), new Turn(Team.WHITE));
		board.move(Position.of("b5"), Position.of("b4"), new Turn(Team.BLACK));
		board.move(Position.of("c5"), Position.of("c6"), new Turn(Team.WHITE));
		board.move(Position.of("b4"), Position.of("b3"), new Turn(Team.BLACK));
		board.move(Position.of("c6"), Position.of("d7"), new Turn(Team.WHITE));
		board.move(Position.of("b3"), Position.of("a2"), new Turn(Team.BLACK));
		board.move(Position.of("d7"), Position.of("e8"), new Turn(Team.WHITE));

		assertThat(board.isKingDead()).isTrue();
	}
}