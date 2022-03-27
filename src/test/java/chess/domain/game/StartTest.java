package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.board.coordinate.Coordinate;

public class StartTest {
	@Test
	void start() {
		State start = new Start();
		State whiteTurn = start.start();
		assertThat(whiteTurn).isInstanceOf(WhiteTurn.class);
	}

	@Test
	void end() {
		State start = new Start();
		State End = start.end();
		assertThat(End).isInstanceOf(End.class);
	}

	@Test
	void move() {
		State start = new Start();
		assertThatThrownBy(() -> start.move(Coordinate.of("a2"), Coordinate.of("a4")))
			.isInstanceOf(IllegalStateException.class);
	}
}
