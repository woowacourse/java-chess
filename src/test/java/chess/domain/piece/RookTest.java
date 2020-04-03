package chess.domain.piece;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Position;

class RookTest {
	@DisplayName("생성자에서 side와 position을 인자로 넘겼을 때 인스턴스 생성")
	@Test
	void constructor_SideAndPosition_CreateInstance() {
		assertThat(ROOK_D4).isInstanceOf(Rook.class);
	}

	@DisplayName("Rook과 같은 행이나 같은 열에 있는 position을 인자로 넘겼을 때 true 반환")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "e4", "d3", "c4"})
	void isInPath_SameColumnOrSameRow_True(String position) {
		assertThat(ROOK_D4.isInPath(new Position(position))).isTrue();
	}

	@DisplayName("Rook과 다른 행이나 다른 열에 있는 position을 인자로 넘겼을 때 false 반환")
	@ParameterizedTest
	@ValueSource(strings = {"c5", "e5", "e3", "c3"})
	void isInPath_SameColumnNorSameRow_False(String position) {
		assertThat(ROOK_D4.isInPath(new Position(position))).isFalse();
	}
}