package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnTest {
	@ParameterizedTest
	@MethodSource(value = "provideColumnAndNumber")
	@DisplayName("생성 테스트")
	void of(int input, Column expected) {
		assertThat(Column.of(input)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideColumnAndNumber() {
		return Stream.of(
				Arguments.of(1, Column.ONE),
				Arguments.of(2, Column.TWO),
				Arguments.of(8, Column.EIGHT));
	}

	@ParameterizedTest
	@MethodSource(value = "provideColumnAndString")
	@DisplayName("생성 테스트")
	void ofString(String input, Column expected) {
		assertThat(Column.of(input)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideColumnAndString() {
		return Stream.of(
				Arguments.of("a", Column.ONE),
				Arguments.of("b", Column.TWO),
				Arguments.of("h", Column.EIGHT));
	}

	@ParameterizedTest
	@CsvSource(value = {"1,8,7", "6,6,0", "6,4,2"})
	@DisplayName("거리(절대값) 계산 테스트")
	void calculateDistance(int source, int target, int expected) {
		assertThat(Column.of(source).calculateDistance(Column.of(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,8,-7", "6,6,0", "6,4,2"})
	@DisplayName("거리 계산 테스트")
	void calculateDifference(int source, int target, int expected) {
		assertThat(Column.of(source).calculateDifference(Column.of(target))).isEqualTo(expected);
	}
}