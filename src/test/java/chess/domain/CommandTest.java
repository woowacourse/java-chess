package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessgame.Command;
import chess.domain.position.Position;

public class CommandTest {

	@Test
	void create() {
		assertThatThrownBy(() -> new Command("asd"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void validateStartTest() {
		Command command = new Command("move b1 b2");
		assertThatThrownBy(() -> command.validateStart())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void getPositionTest() {
		Command command = new Command("move h1 c5");

		assertThat(command.createStartPosition()).isEqualTo(Position.of(1, 8));
		assertThat(command.createTargetPosition()).isEqualTo(Position.of(5, 3));
	}
}
