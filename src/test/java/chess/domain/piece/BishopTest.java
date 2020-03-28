package chess.domain.piece;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Position;

class BishopTest {
	@DisplayName("생성자에서 side와 position을 인자로 넘겼을 때 인스턴스 생성")
	@Test
	void constructor_SideAndPosition_CreateInstance() {
		assertThat(BISHOP_D4).isInstanceOf(Bishop.class);
	}

	@DisplayName("isInPath에서 Bishop의 대각선 상에 있는 position을 인자로 넘겼을 때 true 반환")
	@ParameterizedTest
	@ValueSource(strings = {"c5", "e5", "c3", "e3"})
	void isInPath_InDiagonal_True(String position) {
		assertThat(BISHOP_D4.isInPath(new Position(position))).isTrue();
	}

	@DisplayName("isInPath에서 Bishop의 대각선 상에 없는 position을 인자로 넘겼을 때 false 반환")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "e4", "d3", "c4"})
	void isInPath_OutOfDiagonal_False(String position) {
		assertThat(BISHOP_D4.isInPath(new Position(position))).isFalse();
	}
}