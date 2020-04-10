package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.position.Position;

class FinishTest {
	private Finish finish = new Finish(BoardRepository.create());

	@Test
	void isEnd() {
		assertThat(finish.isEnd()).isTrue();
	}

	@Test
	void start() {
		assertThatThrownBy(() -> finish.start()).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void move() {
		assertThatThrownBy(() -> finish.move(Position.of("a1"), Position.of("a2")))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void end() {
		assertThatThrownBy(() -> finish.end()).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void board() {
		assertThat(finish.board()).isInstanceOf(Board.class);
	}

	@Test
	void score() {
		assertThat(finish.score(Team.WHITE).getScore()).isEqualTo(38.0);
		assertThat(finish.score(Team.BLACK).getScore()).isEqualTo(38.0);
	}

	@Test
	void turn() {
		assertThatThrownBy(() -> finish.turn()).isInstanceOf(UnsupportedOperationException.class);
	}
}