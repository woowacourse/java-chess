package chess.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void validateNameEmpty() {
		String name = "";

		assertThatThrownBy(() -> new Game(1, name, "start"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("게임 이름은 빈칸일 수 없습니다.");
	}

	@Test
	void validateNameLength() {
		String name = "12345678910";

		assertThatThrownBy(() -> new Game(1, name, "start"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("게임 이름은 10글자를 넘어갈 수 없습니다.");
	}
}
