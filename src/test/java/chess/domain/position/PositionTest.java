package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
	@Test
	@DisplayName("생성 테스트 - 좌표")
	void constructString() {
		assertThat(new Position("a3")).isEqualTo(new Position(1, 3));
	}

	@Test
	@DisplayName("생성 테스트 - 열 문자, 행 숫자")
	void constructStringInt() {
		assertThat(new Position("a", 3)).isEqualTo(new Position(1, 3));
	}

	@ParameterizedTest
	@MethodSource(value = "provideDirection")
	@DisplayName("올바른 방향을 찾는지 테스트")
	void findDirection(Direction expected, Position position1, Position position2) {
		assertThat(position1.findDirection(position2)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDirection() {
		return Stream.of(
				Arguments.of(Direction.COL, new Position("a8"), new Position("a7")),
				Arguments.of(Direction.ROW, new Position("a8"), new Position("b8")),
				Arguments.of(Direction.NEGATIVE_DIAGONAL, new Position("a5"), new Position("e1")),
				Arguments.of(Direction.POSITIVE_DIAGONAL, new Position("c5"), new Position("e7")),
				Arguments.of(Direction.NON_LINEAR, new Position("a8"), new Position("d7"))
		);
	}

	@Test
	@DisplayName("같은 행인지 테스")
	void isSameRow() {
		assertThat(new Position("a8").isSameRow(new Position("b8"))).isTrue();
	}

	@Test
	@DisplayName("같은 행인지 테스트 - 행")
	void testIsSameRow() {
		assertThat(new Position("a8").isSameRow(Row.of(8))).isTrue();
	}

	@Test
	@DisplayName("같은 열인지 테스트")
	void isSameCol() {
		assertThat(new Position("a8").isSameCol(new Position("a6"))).isTrue();
	}

	@Test
	@DisplayName("같은 열인지 테스트 - 열")
	void testIsSameCol() {
		assertThat(new Position("a8").isSameCol(Column.of("a"))).isTrue();
	}

	@Test
	@DisplayName("같은 대각선(Positive)에 있는지 확인")
	void isPositiveDiagonal() {
		assertThat(new Position("a1").isPositiveDiagonal(new Position("c3"))).isTrue();
	}

	@Test
	@DisplayName("같은 대각선(Positive)에 있는지 확인")
	void isNegativeDiagonal() {
		assertThat(new Position("c3").isNegativeDiagonal(new Position("a5"))).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a3,a8,5", "a1,b2,1", "a2,c3,2"})
	@DisplayName("가로 세로 길이 중 최대 거리를 찾기")
	void calculateMaxDistance(String position1, String position2, int expected) {
		assertThat(new Position(position1).calculateMaxDistance(new Position(position2))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"a3,a8,6,true", "a1,b2,0,false", "a2,c3,2,true"})
	@DisplayName("일정 거리 내에 있는 지 찾는 기능 테스트")
	void isInDistance(String position1, String position2, int distance, boolean expected) {
		assertThat(new Position(position1).isInDistance(distance, new Position(position2))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"a3,a8,-5", "a1,b1,0", "a5,c3,2"})
	void compareToRow(String position1, String position2, int expected) {
		assertThat(new Position(position1).compareToRow(new Position(position2))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"a3,a8,0", "a1,b1,-1", "e5,c3,2"})
	void compareToCol(String position1, String position2, int expected) {
		assertThat(new Position(position1).compareToCol(new Position(position2))).isEqualTo(expected);
	}
}