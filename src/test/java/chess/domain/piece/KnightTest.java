package chess.domain.piece;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Position;

class KnightTest {
	@DisplayName("생성자에서 side와 position을 인자로 넘겼을 때 인스턴스 생성")
	@Test
	void constructor_SideAndPosition_CreateInstance() {
		assertThat(KNIGHT_D4).isInstanceOf(Knight.class);
	}

	@DisplayName("isInPath에서 Knight와 직선 상에 없고 2칸 범위 안에 있는 position을 인자로 넘겼을 때 true 반환")
	@ParameterizedTest
	@ValueSource(strings = {"c6", "e6", "f5", "f3", "e2", "c2", "b3", "b5"})
	void isInPath_NotLinearAndInDistance_True(String position) {
		assertThat(KNIGHT_D4.isInPath(new Position(position))).isTrue();
	}

	@DisplayName("isInPath에서 Knight와 직선 상에 있거나 2칸 범위 밖에 있는 position을 인자로 넘겼을 때 false 반환")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "e5", "e4", "e3", "d3", "c3", "c4", "c5"})
	void isInPath_LinearOrOutOfDistance_False(String position) {
		assertThat(KNIGHT_D4.isInPath(new Position(position))).isFalse();
	}
}
