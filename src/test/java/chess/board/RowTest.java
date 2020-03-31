package chess.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {

	@DisplayName("잘못된 Row를 입력하면 Exception")
	@ParameterizedTest
	@ValueSource(ints = {0, 9})
	void of(int number) {
		assertThatThrownBy(() -> Row.of(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Row와 Row 차이 계산")
	@Test
	void minus() {
		Row five = Row.FIVE;
		int actual = five.minus(Row.ONE);
		int four = 4;

		assertThat(actual).isEqualTo(four);
	}

	@DisplayName("thisRow가 otherRow보다 큰 값인지 확인")
	@Test
	void isGreaterThan() {
		Row five = Row.FIVE;
		Row four = Row.FOUR;

		assertThat(five.isGreaterThan(four)).isTrue();
	}

	@Test
	void add() {
		Row five = Row.FIVE;
		assertThat(five.add(3)).isEqualTo(Row.EIGHT);
	}
}