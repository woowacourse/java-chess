package chess.domain;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SideTest {
	@ParameterizedTest
	@MethodSource("providePawn")
	@DisplayName("폰이 초기 위치에 있는 지 테스트")
	void isInitPawnRow(Side side, Position position, boolean expected) {
		assertThat(side.isInitPawnRow(position)).isEqualTo(expected);
	}

	private static Stream<Arguments> providePawn() {
		return Stream.of(
				Arguments.of(Side.WHITE, new Position("a2"), true),
				Arguments.of(Side.BLACK, new Position("d7"), true));
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,false", "c3,a1,false", "c5,c4,false", "a3,a8,true", "d6,a1,false"})
	@DisplayName("진영별 공격방향인지 확인하는 기능 테스트 - 백")
	void isAttackForwardWhite(String source, String target, boolean expected) {
		assertThat(Side.WHITE.isAttackForward(new Position(source), new Position(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,true", "c3,a1,true", "c5,c4,true", "a3,a8,false", "d6,a1,true"})
	@DisplayName("진영별 공격방향인지 확인하는 기능 테스트 - 흑")
	void isAttackForwardBlack(String source, String target, boolean expected) {
		assertThat(Side.BLACK.isAttackForward(new Position(source), new Position(target))).isEqualTo(expected);
	}

	@Test
	@DisplayName("상대 진영을 나타내는지 테스트")
	void reverse() {
		assertThat(Side.BLACK.reverse()).isEqualTo(Side.WHITE);
		assertThat(Side.WHITE.reverse()).isEqualTo(Side.BLACK);
	}
}