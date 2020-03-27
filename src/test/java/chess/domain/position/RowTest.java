package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {
	@ParameterizedTest
	@MethodSource(value = "provideRowAndNumber")
	@DisplayName("생성 테스트")
	void of(int input, Row expected) {
		assertThat(Row.of(input)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideRowAndNumber() {
		return Stream.of(
				Arguments.of(1, Row.ONE),
				Arguments.of(2, Row.TWO),
				Arguments.of(8, Row.EIGHT));
	}

	@ParameterizedTest
	@CsvSource(value = {"1,8,7", "6,6,0", "6,4,2"})
	@DisplayName("거리(절대값) 계산 테스트")
	void calculateDistance(int source, int target, int expected) {
		assertThat(Row.of(source).calculateDistance(Row.of(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,8,-7", "6,6,0", "6,4,2"})
	@DisplayName("거리 계산 테스트")
	void calculateDifference(int source, int target, int expected) {
		assertThat(Row.of(source).calculateDifference(Row.of(target))).isEqualTo(expected);
	}
}