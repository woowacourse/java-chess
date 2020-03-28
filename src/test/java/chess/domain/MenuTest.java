package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

public class MenuTest {

	@Test
	void create() {
		assertThatThrownBy(() -> new Menu("asd"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void validateStartTest() {
		Menu menu = new Menu("move b1 b2");
		assertThatThrownBy(() -> menu.validateStart())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void getPositionTest() {
		Menu menu = new Menu("move h1 c5");

		assertThat(menu.getStartPosition()).isEqualTo(Position.of(1, 8));
		assertThat(menu.getTargetPosition()).isEqualTo(Position.of(5, 3));
	}
}
