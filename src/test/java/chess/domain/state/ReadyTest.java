package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class ReadyTest {
	private Ready ready;

	@BeforeEach
	void setUp() {
		ready = new Ready();
	}

	@Test
	void isEnd() {
		assertThat(ready.isEnd()).isFalse();
	}

	@Test
	void start() {
		assertThat(ready.start()).isInstanceOf(Playing.class);
	}

	@Test
	void move() {
		assertThatThrownBy(() -> ready.move(Position.of("a1"), Position.of("a2")))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void end() {
		assertThatThrownBy(() -> ready.end()).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void board() {
		assertThatThrownBy(() -> ready.board()).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void score() {
		assertThatThrownBy(() -> ready.score(Team.WHITE)).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void turn() {
		assertThatThrownBy(() -> ready.turn()).isInstanceOf(UnsupportedOperationException.class);
	}
}