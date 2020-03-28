package chess.domain.piece;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Position;

class KingTest {
	@DisplayName("생성자에서 side와 position을 인자로 넘겼을 때 인스턴스 생성")
	@Test
	void constructor_SideAndPosition_CreateInstance() {
		assertThat(KING_D4).isInstanceOf(King.class);
	}

	@DisplayName("isInPath에서 King과 1칸 범위 안에 있는 position을 인자로 넘겼을 때 true 반환")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "e5", "e4", "e3", "d3", "c3", "c4", "c5"})
	void isInPath_InDistancePosition_True(String position) {
		assertThat(KING_D4.isInPath(new Position(position))).isTrue();
	}

	@DisplayName("isInPath에서 King과 1칸 범위 밖에 있는 position을 인자로 넘겼을 때 false 반환")
	@ParameterizedTest
	@ValueSource(strings = {"d6", "e6", "f6", "f5", "f4", "f3", "f2", "e2", "d2", "c2", "b2", "b3", "b4", "b5", "b6",
			"c6"})
	void isInPath_OutOfDistance_False(String position) {
		assertThat(KING_D4.isInPath(new Position(position))).isFalse();
	}
}
