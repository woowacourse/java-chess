package chess.domain.piece;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Position;

class PawnTest {
	@DisplayName("생성자에서 side와 position을 인자로 넘겼을 때 인스턴스 생성")
	@Test
	void constructor_SideAndPosition_CreateInstance() {
		assertThat(PAWN_D4).isInstanceOf(Pawn.class);
	}

	@DisplayName("isPawnPath에서 Pawn과 같은 열에 있고 1칸 범위 안에 있는 Position을 인자로 넘겼을 때 true 반환")
	@Test
	void isPawnPath_SameColumnAndInDistance_True() {
		assertThat(PAWN_D4.isInPath(new Position("d5"))).isTrue();
	}

	@DisplayName("isPawnPath에서 Pawn과 같은 열에 없거나 1칸 범위 밖에 있는 Position을 인자로 넘겼을 때 false 반환")
	@ParameterizedTest
	@ValueSource(strings = {"c5", "d6"})
	void isPawnPath_NotSameColumnOrOutOfDistance_False(String position) {
		assertThat(PAWN_D4.isInPath(new Position(position))).isFalse();
	}

	@DisplayName("isFirstState에서 Pawn이 최초 위치에 있고, Pawn과 같은 열에 있고 2칸 범위 안에 있는 Position을 인자로 넘겼을 때 true 반환")
	@ParameterizedTest
	@ValueSource(strings = {"d3", "d4"})
	void isFirstState_SameColumnAndInDistance_True(String position) {
		assertThat(PAWN_D2.isInPath(new Position(position))).isTrue();
	}

	@DisplayName("isFirstState에서 Pawn이 최초 위치에 있고, Pawn과 같은 열에 없거나 2칸 범위 밖에 있는 Position을 인자로 넘겼을 때 false 반환")
	@ParameterizedTest
	@ValueSource(strings = {"c3", "d5"})
	void isFirstState_NotSameColumnOrOutOfDistance_False(String position) {
		assertThat(PAWN_D2.isInPath(new Position(position))).isFalse();
	}
}
